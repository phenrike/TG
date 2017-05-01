using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Runtime.Serialization;

namespace WebAPIMainP.Models
{
    [DataContract(Name = "Compartilhamento", Namespace = "")]
    public class Compartilhamento
    {
        [DataMember]
        public virtual int Id { get; set; }

        [DataMember]
        public virtual int Emissor { get; set; }

        [DataMember]
        public virtual int Receptor { get; set; }
    }
}