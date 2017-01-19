using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebAPIMainP.Models
{
    public class Busca
    {
        public IList<Usuario> buscarUsuario(int rede, String busca)
        {
            Conexao con = new Conexao();
            IList<Usuario> usuarios = con.buscarUsuario(rede, busca);
            return usuarios;
        }
    }
}