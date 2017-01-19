using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebAPIMainP.Models
{
    public class Usuario
    {
        public virtual int Id { get; set; }
        public virtual string Login { get; set; }
        public virtual string Senha { get; set; }
        public virtual string Nome { get; set; }
        public virtual string Sexo { get; set; }
        public virtual string Face { get; set; }
        public virtual Boolean Facepublic { get; set; }
        public virtual string Wpp { get; set; }
        public virtual Boolean Wpppublic { get; set; }
        public virtual string Insta { get; set; }
        public virtual Boolean Instapublic { get; set; }
        public virtual string Snap { get; set; }
        public virtual Boolean Snappublic { get; set; }
        public virtual string Twitter { get; set; }
        public virtual Boolean Twitterpublic { get; set; }
        public virtual string Email { get; set; }
        public virtual Boolean Emailpublic { get; set; }
        public virtual string Link { get; set; }
        public virtual Boolean Linkpublic { get; set; }
        public virtual DateTime Dtinscricao { get; set; }
    }
}