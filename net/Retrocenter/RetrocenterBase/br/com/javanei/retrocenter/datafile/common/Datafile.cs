using System;
using System.Collections.Generic;
using System.Text;
using RetrocenterBase.br.com.javanei.retrocenter.common;

namespace RetrocenterBase.br.com.javanei.retrocenter.datafile.common
{
    class Datafile : DatafileObject
    {
        public string Name { get; set; }
        public string Catalog { get; set; }
        public string Version { get; set; }
        public string Description { get; set; }
        public string Author { get; set; }
        public string Date { get; set; }
        public string Email { get; set; }
        public string Homepage { get; set; }
        public string Url { get; set; }
        public string Comment { get; set; }
        private HashSet<DatafileArtifact> Artifacts { get; set; } = new HashSet<DatafileArtifact>();
        public string PlatformName { get; set; }

        public Datafile(string name)
        {
            Name = name;
        }
        public Datafile(string name, string catalog, string version, string description, string author, string date,
            string email, string homepage, string url, string comment)
        {
            Name = name;
            SetCatalog(catalog);
            Version = version;
            Description = description;
            Author = author;
            Date = date;
            Email = email;
            Homepage = homepage;
            Url = url;
            Comment = comment;
        }

        public Datafile ToDatafile()
        {
            return this;
        }

        public string toFile()
        {
            StringBuilder sb = new StringBuilder();
            sb.Append("<?xml version=\"1.0\"?>\n");
            sb.Append("<retrocenter");
            appendXMLAttributeIfNotNull(sb, "name", Name);
            appendXMLAttributeIfNotNull(sb, "catalog", Catalog);
            appendXMLAttributeIfNotNull(sb, "version", Version);
            sb.Append(">\n");
            appendXMLTagIfNotNull(sb, "description", Description, 1);
            appendXMLTagIfNotNull(sb, "author", Author, 1);
            appendXMLTagIfNotNull(sb, "date", Date, 1);
            appendXMLTagIfNotNull(sb, "email", Email, 1);
            appendXMLTagIfNotNull(sb, "homepage", Homepage, 1);
            appendXMLTagIfNotNull(sb, "url", Url, 1);
            appendXMLTagIfNotNull(sb, "comment", Comment, 1);

            foreach (var game in Artifacts)
            {
                sb.Append("\t<artifact");
                appendXMLAttributeIfNotNull(sb, "name", game.Name);
                appendXMLAttributeIfNotNull(sb, "description", game.Description);
                appendXMLAttributeIfNotNull(sb, "year", game.Year);
                sb.Append(">\n");
                foreach(var key in game.Fields.Keys) {
                    appendXMLTagIfNotNull(sb, key, game.GetField(key), 2);
                }

                foreach (var file in game.Files)
                {
                    sb.Append("\t\t<file");
                    appendXMLAttributeIfNotNull(sb, "type", file.Type);
                    appendXMLAttributeIfNotNull(sb, "name", file.Name);
                    appendXMLAttributeIfNotNull(sb, "size", file.Size);
                    appendXMLAttributeIfNotNull(sb, "crc", file.Crc);
                    appendXMLAttributeIfNotNull(sb, "sha1", file.Sha1);
                    appendXMLAttributeIfNotNull(sb, "md5", file.Md5);
                    appendXMLAttributeIfNotNull(sb, "date", file.Date);
                    if (game.Fields.Count > 0)
                    {
                        sb.Append(">\n");
                        foreach (var key in game.Fields.Keys)
                        {
                            appendXMLTagIfNotNull(sb, key, file.GetField(key), 3);
                        }
                        sb.Append("\t\t</file>\n");
                    }
                    else
                    {
                        sb.Append("/>\n");
                    }
                }

                foreach (var release in game.Releases)
                {
                    sb.Append("\t\t<release");
                    appendXMLAttributeIfNotNull(sb, "name", release.Name);
                    appendXMLAttributeIfNotNull(sb, "region", release.Region);
                    appendXMLAttributeIfNotNull(sb, "language", release.Language);
                    appendXMLAttributeIfNotNull(sb, "date", release.Date);
                    appendXMLAttributeIfNotNull(sb, "default", release.Default);
                    sb.Append("/>\n");
                }

                sb.Append("\t</artifact>\n");
            }

            sb.Append("</retrocenter>\n");
            return sb.ToString();
        }

        public bool addArtifact(DatafileArtifact artifact)
        {
            return Artifacts.Add(artifact);
        }

        public void SetCatalog(string catalog)
        {
            Nullable<DatafileCatalogEnum> v = DatafileCatalogEnumUtil.FromName(catalog);
            if (v.HasValue)
            {
                SetCatalog(v.Value);
            } else
            {
                Catalog = null;
            }
        }

        public void SetCatalog(DatafileCatalogEnum catalog)
        {
            Catalog = catalog.ToString();
        }

        private static void appendXMLAttributeIfNotNull(StringBuilder sb, string name, Object value)
        {
            if (value != null)
            {
                sb.Append(" ").Append(name).Append("=\"").Append(value).Append("\"");
            }
        }

        private static void appendXMLTagIfNotNull(StringBuilder sb, String name, Object value, int ident)
        {
            if (value != null)
            {
                for (int i = 0; i < ident; i++)
                {
                    sb.Append("\t");
                }
                sb.Append("<").Append(name).Append(">").Append(value).Append("</").Append(name).Append(">\n");
            }
        }

        public override bool Equals(object obj)
        {
            var datafile = obj as Datafile;
            return datafile != null &&
                   Name == datafile.Name &&
                   Catalog == datafile.Catalog &&
                   Version == datafile.Version;
        }

        public override int GetHashCode()
        {
            var hashCode = -1485249515;
            hashCode = hashCode * -1521134295 + EqualityComparer<string>.Default.GetHashCode(Name);
            hashCode = hashCode * -1521134295 + EqualityComparer<string>.Default.GetHashCode(Catalog);
            hashCode = hashCode * -1521134295 + EqualityComparer<string>.Default.GetHashCode(Version);
            return hashCode;
        }
    }
}
