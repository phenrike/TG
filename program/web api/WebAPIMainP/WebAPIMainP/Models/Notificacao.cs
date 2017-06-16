using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WebAPIMainP.Models
{
    public interface Notificacao
    {
        String tipo { get; set; }
        Usuario emissor { get; set; }
        DateTime dataEhora { get; set; }
    }
}