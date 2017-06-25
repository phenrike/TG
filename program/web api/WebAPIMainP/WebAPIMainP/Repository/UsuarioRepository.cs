using FluentNHibernate.Cfg;
using FluentNHibernate.Cfg.Db;
using NHibernate;
using NHibernate.Criterion;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using WebAPIMainP.Models;

namespace WebAPIMainP.Repository
{
    public class UsuarioRepository
    {
        private ISessionFactory sessionFactory;
        private ISession session;
        private ITransaction transaction;

        public UsuarioRepository()
        {
            this.sessionFactory = CreateSessionFactory();
        }

        private static ISessionFactory CreateSessionFactory()
        {
            ISessionFactory isessionFactory = Fluently.Configure()
        .Database(MySQLConfiguration.Standard.ConnectionString(@"Server=mysql08-farm68.kinghost.net;Database=devphps;Uid=devphps;Pwd=h4g4n4h;"))
        .Mappings(m => m
        .FluentMappings.AddFromAssemblyOf<UsuarioMap>())
        .BuildSessionFactory();

            return isessionFactory;
        }

        private void AbrirConexao()
        {
            this.session = this.sessionFactory.OpenSession();
        }

        private void IniciarTransacao()
        {
            AbrirConexao();
            this.transaction = this.session.BeginTransaction();
        }

        private void FinalizarTransacao()
        {
            this.transaction.Commit();
            FecharConexao();
        }

        private void FecharConexao()
        {
            this.session.Close();
        }

        public void Salvar(Usuario usuario)
        {
            IniciarTransacao();

            // Variável para pegar a data e hora de cadastro do usuário
            DateTime localDate = DateTime.Now;
            usuario.Dtinscricao = localDate;

            //Adiciona o id do usuário nos usernames
            foreach (Username username in usuario.Usernames)
            {
                username.Idusuario = usuario;
            }

            this.session.Save(usuario);
            FinalizarTransacao();
        }

        public void Alterar(Usuario usuario)
        {
            IniciarTransacao();

            foreach (Username username in usuario.Usernames)
            {
                username.Idusuario = usuario;
            }

            this.session.Update(usuario);
            FinalizarTransacao();
        }

        public void Excluir(Usuario usuario)
        {
            IniciarTransacao();

            foreach (Username username in usuario.Usernames)
            {
                username.Idusuario = usuario;
            }

            this.session.Delete(usuario);
            FinalizarTransacao();
        }

        public Usuario CarregarUsuario(String login, String senha)
        {
            AbrirConexao();
            Usuario usuario = new Usuario();
            IQueryOver<Usuario> qo;
            qo = this.session.QueryOver<Usuario>().Where(x => x.Login == login).And(x => x.Senha == senha);
            usuario = qo.SingleOrDefault<Usuario>();
            FecharConexao();

            if (usuario != null)
            {
                // Se o usuário for atualizado com novas redes sociais ele é carregado novamente
                if (VerificarExistenciaRedesSociais(usuario))
                {
                    AbrirConexao();
                    qo = this.session.QueryOver<Usuario>().Where(x => x.Login == login).And(x => x.Senha == senha);
                    usuario = qo.SingleOrDefault<Usuario>();
                    FecharConexao();
                }

            }
            
            return usuario;
        }

        public IList<Usuario> BuscarUsuarios(int redeSocial, String busca)
        {
            AbrirConexao();
            IList<Usuario> listaConsulta = null;
                      
            // ID | REDE SOCIAL
            //  1 | Facebook
            //  2 | WhatsApp
            //  3 | Instagram
            //  4 | Snapchat
            //  5 | Twitter
            //  6 | YouTube
            //  7 | E-mail
            //  8 | Link

            IQueryOver<Usuario,Username> qoUsuario = this.session.QueryOver<Usuario>().JoinQueryOver<Username>(u => u.Usernames).Where(un => un.Idredesocial.Id == redeSocial).And(un => un.Nomeusuario.IsLike(busca, MatchMode.Anywhere));
            listaConsulta = qoUsuario.List<Usuario>();

            FecharConexao();

            return listaConsulta;
        }

        public void CompartilharPerfil(Compartilhamento compartilhamento)
        {
            IniciarTransacao();

            // Variável para pegar a data e hora da notificação
            DateTime localDate = DateTime.Now;
            compartilhamento.Dataehora = localDate;

            //Adiciona o id do usuário nos usernames
            foreach (Username username in compartilhamento.Emissor.Usernames)
            {
                username.Idusuario = compartilhamento.Emissor;
            }

            foreach (Username username in compartilhamento.Receptor.Usernames)
            {
                username.Idusuario = compartilhamento.Receptor;
            }

            this.session.Save(compartilhamento);
            FinalizarTransacao();
        }

        public IList<Compartilhamento> CarregarCompartilhamentos(Usuario receptor)
        {
            AbrirConexao();
            IList<Compartilhamento> listaConsulta = null;
            IQueryOver<Compartilhamento> qo;
            Usuario usuario = null;

            qo = this.session.QueryOver<Compartilhamento>().JoinAlias(x => x.Emissor, () => usuario).Where(x => x.Receptor.Id == receptor.Id);
            listaConsulta = qo.List<Compartilhamento>();

            FecharConexao();

            return listaConsulta;
        }

        public Boolean VerificarExistenciaRedesSociais(Usuario usuario)
        {
            /* Esse método verifica se o usuário possui todas as redes sociais disponíveis na base, caso o usuário não tenha 
             * alguma rede social, essa rede social será adicionada no perfil dele com o nome de usuário vazio. */
            AbrirConexao();

            Boolean Atualizado = false;
            IList<Redesocial> redesSociais = null;
            IQueryOver<Redesocial> qoRedes;
            IList<int> redesSociaisUsuario = new List<int>();

            qoRedes = this.session.QueryOver<Redesocial>();
            redesSociais = qoRedes.List<Redesocial>();
            FecharConexao();
            
            foreach (Username un in usuario.Usernames)
            {
                redesSociaisUsuario.Add(un.Idredesocial.Id);
            }

            foreach (Redesocial rs in redesSociais)
            {
                if (!redesSociaisUsuario.Contains(rs.Id))
                {
                    Username un = new Username();
                    un.Idredesocial = rs;
                    un.Nomeusuario = "";
                    usuario.AdicionarUsername(un);
                    Alterar(usuario);
                    Atualizado = true;
                }
            }

            

            return Atualizado;
        }

        public Boolean VerificarExistenciaLogin(String login)
        {
            AbrirConexao();
            Usuario usuario = new Usuario();
            IQueryOver<Usuario> qo;
            qo = this.session.QueryOver<Usuario>().Where(x => x.Login == login);
            usuario = qo.SingleOrDefault<Usuario>();
            FecharConexao();

            if (usuario == null)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
    }
}