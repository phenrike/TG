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
    [Authorize]
    [RoutePrefix("api/mainp")]
    public class UsuarioController : ApiController
    {
        UsuarioRepository usuarioRepository;

        public UsuarioController()
        {
            usuarioRepository = new UsuarioRepository();
        }

        [AllowAnonymous]
        [AcceptVerbs("POST")]
        [Route("usuarios")]
        public void CadastrarUsuario(Usuario usuario)
        {
            usuarioRepository.Salvar(usuario);
        }

        [AcceptVerbs("POST")]
        [Route("usuarios/carregar")]
        public Usuario CarregarUsuario()
        {
            var request = Request;
            var headers = request.Headers;
            var login = string.Empty;
            var senha = string.Empty;

            if (headers.Contains("login") && headers.Contains("senha"))
            {
                login = headers.GetValues("login").First();
                senha = headers.GetValues("senha").First();
            }

            return usuarioRepository.CarregarUsuario(login, senha);
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
        public IList<Usuario> BuscarUsuarios(int redeSocial, String busca)
        {
            return usuarioRepository.BuscarUsuarios(redeSocial, busca); ;
        }

        [AcceptVerbs("POST")]
        [Route("usuarios/compartilhar")]
        public void CompartilharPerfil(Compartilhamento compartilhamento)
        {
            usuarioRepository.CompartilharPerfil(compartilhamento);
        }

        [AcceptVerbs("POST")]
        [Route("usuarios/notificacoes")]
        public IList<Notificacao> CarregarNotificacoes(Usuario receptor)
        {
            IList<Notificacao> notificacoes = new List<Notificacao>();
            IList<Compartilhamento> compartilhamentos = new List<Compartilhamento>();

            compartilhamentos = usuarioRepository.CarregarCompartilhamentos(receptor);

            foreach (Compartilhamento c in compartilhamentos)
            {
                NotificacaoCompartilhamento nc = new NotificacaoCompartilhamento(c);
                notificacoes.Add(nc);
            }

            return notificacoes;            
        }

        [AllowAnonymous]
        [AcceptVerbs("POST")]
        [Route("usuarios/login/{login}")]
        public Boolean VerificarExistenciaLogin(String login)
        {
            return usuarioRepository.VerificarExistenciaLogin(login);
        }
    }
}
