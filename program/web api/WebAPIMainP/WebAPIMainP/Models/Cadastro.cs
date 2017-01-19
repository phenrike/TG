using FluentNHibernate.Cfg;
using FluentNHibernate.Cfg.Db;
using MySql.Data.MySqlClient;
using NHibernate;
using NHibernate.Cfg;
using NHibernate.Tool.hbm2ddl;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web;

namespace WebAPIMainP.Models
{
    public class Cadastro
    {
        public void cadastrarUsuario(Usuario usuario)
        {
            Conexao con = new Conexao();

            // Variável para pegar a data e hora de cadastro do usuário
            DateTime localDate = DateTime.Now;
            usuario.Dtinscricao = localDate;

            con.salvarUsuario(usuario);
        }
    }
}