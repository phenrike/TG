using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Web;

namespace WebAPIMainP.Models
{
    public class Redesocial
    {
        [DataMember]
        public virtual int Id { get; set; }
        
        [DataMember]
        public virtual String Nome { get; set; }
    }
}