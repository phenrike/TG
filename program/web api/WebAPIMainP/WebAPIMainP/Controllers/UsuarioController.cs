using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using WebAPIMainP.Models;

namespace WebAPIMainP.Controllers
{
    [RoutePrefix("api/mainp")]
    public class UsuarioController : ApiController
    {
        [AcceptVerbs("POST")]
        [Route("usuarios/{usuario}")]
        public void CadastrarUsuario(Usuario usuario)
        {
            Cadastro cadastro = new Cadastro();
            cadastro.cadastrarUsuario(usuario);
        }

        [AcceptVerbs("POST")]
        [Route("usuarios/{login}/senha")]
        public void Logar(String login, String senha)
        {
            
        }

        [AcceptVerbs("PUT")]
        [Route("usuarios")]
        public void AlterarUsuario(Usuario usuario)
        {
            //AÇÃO
        }

        [AcceptVerbs("DELETE")]
        [Route("usuarios/{codigo}")]
        public void ExcluirUsuario(int codigo)
        {
            //AÇÃO
        }

        [AcceptVerbs("GET")]
        [Route("usuarios/{redeSocial}/{busca}")]
        public IList<Usuario> buscarUsuario(int redeSocial, String busca)
        {
            IList<Usuario> usuarios = null;
            Busca b = new Busca();
            usuarios = b.buscarUsuario(redeSocial, busca);
            return usuarios;
        }
    }
}
