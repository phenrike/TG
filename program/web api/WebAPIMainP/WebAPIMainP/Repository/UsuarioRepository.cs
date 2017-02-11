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
        .Database(MySQLConfiguration.Standard.ConnectionString(@"Server=localhost;Database=nsmainp;Uid=root;Pwd=1234;"))
        .Mappings(m => m
        .FluentMappings.AddFromAssemblyOf<UsuarioMap>())
        .BuildSessionFactory();

            return isessionFactory;
        }

        private void abrirConexao()
        {
            this.session = this.sessionFactory.OpenSession();
        }

        private void iniciarTransacao()
        {
            abrirConexao();
            this.transaction = this.session.BeginTransaction();
        }

        private void fazerCommit()
        {
            this.transaction.Commit();
            fecharConexao();
        }

        private void fecharConexao()
        {
            this.session.Close();
        }

        public void Salvar(Usuario usuario)
        {
            iniciarTransacao();

            // Variável para pegar a data e hora de cadastro do usuário
            DateTime localDate = DateTime.Now;
            usuario.Dtinscricao = localDate;

            this.session.Save(usuario);
            fazerCommit();
        }

        public void Alterar(Usuario usuario)
        {
            iniciarTransacao();
            this.session.Update(usuario);
            fazerCommit();
        }

        public Usuario Buscar(String login, String senha)
        {
            abrirConexao();
            Usuario usuario = new Usuario();
            IQueryOver<Usuario> qo;
            qo = this.session.QueryOver<Usuario>().Where(x => x.Login == login).And(x => x.Senha == senha);
            usuario = qo.SingleOrDefault<Usuario>();
            fecharConexao();
            return usuario;
        }

        public void Excluir(Usuario usuario)
        {
            iniciarTransacao();
            this.session.Delete(usuario);
            fazerCommit();
        }

        public IList<Usuario> BuscarUsuarios(int rede, String busca)
        {
            abrirConexao();
            IList<Usuario> listaConsulta = null;
            IQueryOver<Usuario> qo;

            switch (rede)
            {
                // Facebook
                case 0:
                    qo = this.session.QueryOver<Usuario>().Where(Restrictions.On<Usuario>(x => x.Face).IsLike(busca, MatchMode.Anywhere));
                    listaConsulta = qo.List<Usuario>();
                    break;

                //WhatsApp
                case 1:
                    qo = this.session.QueryOver<Usuario>().Where(Restrictions.On<Usuario>(x => x.Wpp).IsLike(busca, MatchMode.Anywhere));
                    listaConsulta = qo.List<Usuario>();
                    break;

                //Instagram
                case 2:
                    qo = this.session.QueryOver<Usuario>().Where(Restrictions.On<Usuario>(x => x.Insta).IsLike(busca, MatchMode.Anywhere));
                    listaConsulta = qo.List<Usuario>();
                    break;

                //Snapchat
                case 3:
                    qo = this.session.QueryOver<Usuario>().Where(Restrictions.On<Usuario>(x => x.Snap).IsLike(busca, MatchMode.Anywhere));
                    listaConsulta = qo.List<Usuario>();
                    break;

                //Twitter
                case 4:
                    qo = this.session.QueryOver<Usuario>().Where(Restrictions.On<Usuario>(x => x.Twitter).IsLike(busca, MatchMode.Anywhere));
                    listaConsulta = qo.List<Usuario>();
                    break;

                //E-mail
                case 5:
                    qo = this.session.QueryOver<Usuario>().Where(Restrictions.On<Usuario>(x => x.Email).IsLike(busca, MatchMode.Anywhere));
                    listaConsulta = qo.List<Usuario>();
                    break;

                //Link
                case 6:
                    qo = this.session.QueryOver<Usuario>().Where(Restrictions.On<Usuario>(x => x.Link).IsLike(busca, MatchMode.Anywhere));
                    listaConsulta = qo.List<Usuario>();
                    break;
            }

            fecharConexao();

            return listaConsulta;
        }
    }
}