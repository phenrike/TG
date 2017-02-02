using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Runtime.Serialization;

namespace WebAPIMainP.Models
{
    [DataContract(Name = "Usuario", Namespace = "")]
    public class Usuario
    {
        [DataMember]
        public virtual int Id { get; set; }

        [DataMember]
        public virtual string Login { get; set; }

        [DataMember]
        public virtual string Senha { get; set; }

        [DataMember]
        public virtual string Nome { get; set; }

        [DataMember]
        public virtual string Sexo { get; set; }

        [DataMember]
        public virtual string Face { get; set; }

        [DataMember]
        public virtual Boolean Facepublic { get; set; }

        [DataMember]
        public virtual string Wpp { get; set; }

        [DataMember]
        public virtual Boolean Wpppublic { get; set; }

        [DataMember]
        public virtual string Insta { get; set; }

        [DataMember]
        public virtual Boolean Instapublic { get; set; }

        [DataMember]
        public virtual string Snap { get; set; }

        [DataMember]
        public virtual Boolean Snappublic { get; set; }

        [DataMember]
        public virtual string Twitter { get; set; }

        [DataMember]
        public virtual Boolean Twitterpublic { get; set; }

        [DataMember]
        public virtual string Email { get; set; }

        [DataMember]
        public virtual Boolean Emailpublic { get; set; }

        [DataMember]
        public virtual string Link { get; set; }

        [DataMember]
        public virtual Boolean Linkpublic { get; set; }

        [DataMember]
        public virtual DateTime Dtinscricao { get; set; }
    }
}