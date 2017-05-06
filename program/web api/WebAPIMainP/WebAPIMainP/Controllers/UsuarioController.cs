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
        [Route("usuarios/{login}/{senha}")]
        public Usuario CarregarUsuario(String login, String senha)
        {
            return usuarioRepository.Buscar(login, senha);
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

        [AllowAnonymous]
        [AcceptVerbs("POST")]
        [Route("usuarios/compartilhar")]
        public void compartilharPerfil(Compartilhamento compartilhamento)
        {
            usuarioRepository.compartilharPerfil(compartilhamento);
        }

        [AllowAnonymous]
        [AcceptVerbs("POST")]
        [Route("usuarios/notificacoes")]
        public IList<Notificacao> carregarNotificacoes(Usuario receptor)
        {
            IList<Notificacao> notificacoes = new List<Notificacao>();
            IList<Compartilhamento> compartilhamentos = new List<Compartilhamento>();

            compartilhamentos = usuarioRepository.carregarCompartilhamentos(receptor);

            foreach (Compartilhamento c in compartilhamentos)
            {
                NotificacaoCompartilhamento nc = new NotificacaoCompartilhamento(c);
                notificacoes.Add(nc);
            }

            return notificacoes;            
        }
    }
}
