using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebAPIMainP.Models
{
    public class NotificacaoCompartilhamento : Notificacao
    {
        public NotificacaoCompartilhamento(Compartilhamento c)
        {
            usuario = c.Emissor;
            dataEhora = c.Dataehora;
            tipo = "compartilhamento";
        }

        public String tipo { get; set; }

        public Usuario usuario { get; set; }

        public DateTime dataEhora { get; set; }
    }
}