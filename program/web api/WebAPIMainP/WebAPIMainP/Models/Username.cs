using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Web;

namespace WebAPIMainP.Models
{
    [DataContract(Name = "Username", Namespace = "")]
    public class Username
    {
        [DataMember]
        public virtual int Id { get; set; }

        [JsonIgnore]
        [DataMember]
        public virtual Usuario Idusuario { get; set; }

        [DataMember]
        public virtual Redesocial Idredesocial { get; set; }

        [DataMember]
        public virtual String Nomeusuario { get; set; }

        [DataMember]
        public virtual Boolean Privado { get; set; }
    }
}