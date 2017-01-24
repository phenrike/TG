using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebAPIMainP.Models
{
    public class Exclusao
    {
        public void excluirUsuario(Usuario usuario)
        {
            Conexao con = new Conexao();

            con.delete(usuario);
        }
    }
}