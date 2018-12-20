using System;
using System.Collections.Generic;
using System.Text;

namespace RetrocenterBase.br.com.javanei.retrocenter.platform
{
    class Platform
    {
        public string Name { get; set; }
        public string ShortName { get; set; }
        public string StorageFolder { get; set; }
        public HashSet<string> AlternateNames { get; set; } = new HashSet<string>();

        public Platform()
        {
        }

        public Platform(string name)
        {
            this.Name = name;
            this.ShortName = name;
            this.StorageFolder = name;
        }

        public Platform(string name, string shortName)
        {
            this.Name = name;
            this.ShortName = shortName;
            this.StorageFolder = shortName;
        }

        public Platform(string name, string shortName, string storageFolder)
        {
            this.Name = name;
            this.ShortName = shortName;
            this.StorageFolder = storageFolder;
        }

        public Platform(string name, string shortName, string storageFolder, HashSet<string> alternateNames)
        {
            this.Name = name;
            this.ShortName = shortName;
            this.StorageFolder = storageFolder;
            this.AlternateNames = alternateNames;
        }

        public Platform(string name, string shortName, string storageFolder, string[] alternateNames)
        {
            this.Name = name;
            this.ShortName = shortName;
            this.StorageFolder = storageFolder;
            foreach (var t in alternateNames)
            {
                this.AlternateNames.Add(t);
            }
        }

        public void AddAlternateName(string alternateName)
        {
            AlternateNames.Add(alternateName);
        }

        public override string ToString()
        {
            return "Platform{" +
                   "name='" + Name + '\'' +
                   ", shortName='" + ShortName + '\'' +
                   ", storageFolder='" + StorageFolder + '\'' +
                   ", alternateNames=" + AlternateNames +
                   '}';
        }

        public override bool Equals(object obj)
        {
            var platform = obj as Platform;
            return platform != null &&
                   ShortName == platform.ShortName;
        }

        public override int GetHashCode()
        {
            return 1404916966 + EqualityComparer<string>.Default.GetHashCode(ShortName);
        }
    }
}
