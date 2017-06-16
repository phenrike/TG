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
        public virtual DateTime Dtinscricao { get; set; }

        [DataMember]
        public virtual IList<Username> Usernames { get; set; }

        public Usuario()
        {
            Usernames = new List<Username>();
        }

        public virtual void AdicionarUsername(Username un)
        {
            un.Idusuario = this;
            Usernames.Add(un);
        }
    }
}