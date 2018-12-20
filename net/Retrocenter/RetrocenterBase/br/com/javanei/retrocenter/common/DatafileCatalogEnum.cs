using System;
using System.Collections.Generic;
using System.Text;

namespace RetrocenterBase.br.com.javanei.retrocenter.common
{
    enum DatafileCatalogEnum
    {
        NoIntro,
        TOSEC,
        MAME,
        HyperList,
        GoodSet
    }

    class DatafileCatalogEnumUtil
    {
        public static Nullable<DatafileCatalogEnum> FromName(string name)
        {
            if (name == null)
            {
                return null;
            }
            Nullable<DatafileCatalogEnum> r = ParseName(name);
            if (r == null || !r.HasValue)
            {
                throw new ArgumentException(name);
            }
            return r;
        }

        public static bool IsValid(string name)
        {
            return name != null && ParseName(name) != null;
        }

        private static Nullable<DatafileCatalogEnum> ParseName(string name)
        {
            if (name.ToLower().Contains("no-intro") || name.ToLower().Contains("nointro"))
            {
                return DatafileCatalogEnum.NoIntro;
            }
            else if (name.ToUpper().Contains("TOSEC"))
            {
                return DatafileCatalogEnum.TOSEC;
            }
            else if (name.ToUpper().Contains("MAME"))
            {
                return DatafileCatalogEnum.MAME;
            }
            else if (name.ToLower().Equals("hyperlist"))
            {
                return DatafileCatalogEnum.HyperList;
            }
            else if (name.ToLower().Equals("goodset"))
            {
                return DatafileCatalogEnum.GoodSet;
            }
            return null;
        }

    }
}
