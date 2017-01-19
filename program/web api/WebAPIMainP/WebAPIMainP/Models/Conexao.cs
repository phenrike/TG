using FluentNHibernate.Cfg;
using FluentNHibernate.Cfg.Db;
using NHibernate;
using NHibernate.Cfg;
using NHibernate.Criterion;
using NHibernate.Tool.hbm2ddl;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Web;

namespace WebAPIMainP.Models
{
    public class Conexao
    {
        private ISessionFactory sessionFactory;
        private ISession session;
        private ITransaction transaction;

        public Conexao()
        {
            this.sessionFactory = CreateSessionFactory();
            abrirConexao();
            iniciarTransacao();
        }

        private void abrirConexao()
        {
            this.session = this.sessionFactory.OpenSession();
        }

        private void iniciarTransacao()
        {
            this.transaction = this.session.BeginTransaction();
        }

        private void fazerCommit()
        {
            this.transaction.Commit();
        }

        public void salvarUsuario(Usuario u)
        {
            this.session.SaveOrUpdate(u);
            fazerCommit();
        }

        public void alterarUsuario(Usuario u)
        {
            this.session.Update(u);
            fazerCommit();
        }

        public IList<Usuario> buscarUsuario(int rede, String busca)
        {
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

            return listaConsulta;

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
    }
}