using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using FluentNHibernate.Mapping;

namespace WebAPIMainP.Models
{
    public class CompartilhamentoMap : ClassMap<Compartilhamento>
    {
        CompartilhamentoMap()
        {
            Id(x => x.Id);
            References(x => x.Emissor, "EMISSOR").Cascade.All(); ;
            References(x => x.Receptor, "RECEPTOR").Cascade.All(); ;
            Map(x => x.Dataehora);
        }
    }
}