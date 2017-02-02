using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using WebAPIMainP.Models;
using WebAPIMainP.Repository;

namespace WebAPIMainP.Controllers
{
    [RoutePrefix("api/mainp")]
    public class UsuarioController : ApiController
    {
        UsuarioRepository usuarioRepository;

        public UsuarioController()
        {
            usuarioRepository = new UsuarioRepository();
        }

        [AcceptVerbs("POST")]
        [Route("usuarios")]
        public void CadastrarUsuario(Usuario usuario)
        {
            usuarioRepository.Salvar(usuario);
        }

        [AcceptVerbs("POST")]
        [Route("usuarios/{login}/{senha}")]
        public void Logar(String login, String senha)
        {
            
        }

        [AcceptVerbs("PUT")]
        [Route("usuarios")]
        public void AlterarUsuario(Usuario usuario)
        {
            usuarioRepository.Alterar(usuario);
        }

        [AcceptVerbs("DELETE")]
        [Route("usuarios")]
        public void ExcluirUsuario(Usuario usuario)
        {
            usuarioRepository.Excluir(usuario);
        }

        [AcceptVerbs("GET")]
        [Route("usuarios/{redeSocial}/{busca}")]
        public IList<Usuario> buscarUsuario(int redeSocial, String busca)
        {
            return usuarioRepository.BuscarUsuarios(redeSocial, busca); ;
        }
    }
}
