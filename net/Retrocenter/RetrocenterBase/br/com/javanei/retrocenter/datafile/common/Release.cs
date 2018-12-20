using System;
using System.Collections.Generic;
using System.Text;

namespace RetrocenterBase.br.com.javanei.retrocenter.datafile.common
{
    class Release
    {
        public string Name { get; set; }
        public string Region { get; set; }
        public string Language { get; set; }
        public string Date { get; set; }
        public string Default { get; set; } = "no";

        public Release()
        {
        }

        public Release(string name, string region, string language, string date, string _default)
        {
            Name = name;
            Region = region;
            Language = language;
            Date = date;
            Default = _default;
        }


        private static void appendAttributeIfNotNull(StringBuilder sb, string name, Object value)
        {
            if (value != null)
                sb.Append(" ").Append(name).Append("=\"").Append(value).Append("\"");
        }

        public override bool Equals(object obj)
        {
            var release = obj as Release;
            return release != null &&
                   Name == release.Name &&
                   Region == release.Region &&
                   Language == release.Language &&
                   Date == release.Date &&
                   Default == release.Default;
        }

        public override int GetHashCode()
        {
            var hashCode = 201631077;
            hashCode = hashCode * -1521134295 + EqualityComparer<string>.Default.GetHashCode(Name);
            hashCode = hashCode * -1521134295 + EqualityComparer<string>.Default.GetHashCode(Region);
            hashCode = hashCode * -1521134295 + EqualityComparer<string>.Default.GetHashCode(Language);
            hashCode = hashCode * -1521134295 + EqualityComparer<string>.Default.GetHashCode(Date);
            hashCode = hashCode * -1521134295 + EqualityComparer<string>.Default.GetHashCode(Default);
            return hashCode;
        }

        public override string ToString()
        {
            StringBuilder sb = new StringBuilder();
            sb.Append("\t\t<release");
            appendAttributeIfNotNull(sb, "name", Name);
            appendAttributeIfNotNull(sb, "region", Region);
            appendAttributeIfNotNull(sb, "language", Language);
            appendAttributeIfNotNull(sb, "date", Date);
            appendAttributeIfNotNull(sb, "default", Default);
            sb.Append(" />\n");
            return sb.ToString();
        }
    }
}
