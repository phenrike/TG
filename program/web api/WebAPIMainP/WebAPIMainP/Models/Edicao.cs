using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebAPIMainP.Models
{
    public class Edicao
    {
        public void editarUsuario(Usuario usuario)
        {
            Conexao con = new Conexao();

            con.update(usuario);
        }
    }
}