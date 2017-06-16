using FluentNHibernate.Mapping;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebAPIMainP.Models
{
    public class RedesocialMap : ClassMap<Redesocial>
    {
        public RedesocialMap()
        {
            Id(x => x.Id).GeneratedBy.Increment().Not.Nullable();
            Map(x => x.Nome).Length(200).Not.Nullable();
        }

    }
}