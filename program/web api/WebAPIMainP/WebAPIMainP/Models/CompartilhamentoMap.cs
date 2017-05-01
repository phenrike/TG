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
            Map(x => x.Emissor);
            Map(x => x.Receptor);
        }
    }
}