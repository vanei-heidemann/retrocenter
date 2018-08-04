package br.com.javanei.retrocenter.gamedb.launchbox.parser.metadata;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class LBoxMetadataGame implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String overview;
    private Integer maxPlayers;
    private Boolean cooperative;
    private String databaseID;
    private Float communityRating;
    private String platform;
    private List<String> genres = new LinkedList<>();
    private String publisher;
    private String developer;
    private String releaseDate;
    private String esrb;
    private String videoURL;
    private String wikipediaURL;
    private Integer releaseYear;
    private Boolean dos;
    private String startupFile;
    private String startupMD5;
    private String startupParameters;
    private String setupFile;
    private String setupMD5;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(String maxPlayers) {
        if (maxPlayers != null)
            this.maxPlayers = new Integer(maxPlayers);
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public Boolean getCooperative() {
        return cooperative;
    }

    public void setCooperative(String cooperative) {
        if (cooperative != null) {
            this.cooperative = cooperative.equalsIgnoreCase("true") || cooperative.equalsIgnoreCase("yes");
        }
    }

    public void setCooperative(Boolean cooperative) {
        this.cooperative = cooperative;
    }

    public String getDatabaseID() {
        return databaseID;
    }

    public void setDatabaseID(String databaseID) {
        this.databaseID = databaseID;
    }

    public Float getCommunityRating() {
        return communityRating;
    }

    public void setCommunityRating(String communityRating) {
        if (communityRating != null)
            this.communityRating = Float.parseFloat(communityRating);
    }

    public void setCommunityRating(Float communityRating) {
        this.communityRating = communityRating;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        if (genres != null && !genres.trim().isEmpty()) {
            String[] ss = genres.split(";");
            for (String s : ss) {
                this.genres.add(s.trim());
            }
        }
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getEsrb() {
        return esrb;
    }

    public void setEsrb(String esrb) {
        this.esrb = esrb;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getWikipediaURL() {
        return wikipediaURL;
    }

    public void setWikipediaURL(String wikipediaURL) {
        this.wikipediaURL = wikipediaURL;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        if (releaseYear != null && !releaseYear.isEmpty())
            this.releaseYear = new Integer(releaseYear);
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Boolean getDos() {
        return dos;
    }

    public void setDos(String dos) {
        if (dos != null) {
            this.dos = dos.equalsIgnoreCase("true") || dos.equalsIgnoreCase("yes");
        }
    }

    public void setDos(Boolean dos) {
        this.dos = dos;
    }

    public String getStartupFile() {
        return startupFile;
    }

    public void setStartupFile(String startupFile) {
        this.startupFile = startupFile;
    }

    public String getStartupMD5() {
        return startupMD5;
    }

    public void setStartupMD5(String startupMD5) {
        this.startupMD5 = startupMD5;
    }

    public String getStartupParameters() {
        return startupParameters;
    }

    public void setStartupParameters(String startupParameters) {
        this.startupParameters = startupParameters;
    }

    public String getSetupFile() {
        return setupFile;
    }

    public void setSetupFile(String setupFile) {
        this.setupFile = setupFile;
    }

    public String getSetupMD5() {
        return setupMD5;
    }

    public void setSetupMD5(String setupMD5) {
        this.setupMD5 = setupMD5;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LBoxMetadataGame that = (LBoxMetadataGame) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(platform, that.platform)
                && Objects.equals(databaseID, that.databaseID) //TODO: Acho que isso nao deveria estar aqui
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, platform);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  <Game>\n");
        sb.append("    <Name>").append(this.name).append("</Name>\n");
        if (this.releaseYear != null) {
            sb.append("    <ReleaseYear>").append(this.releaseYear).append("</ReleaseYear>\n");
        }
        if (this.releaseDate != null) {
            sb.append("    <ReleaseDate>").append(this.releaseDate).append("</ReleaseDate>\n");
        }
        if (this.overview != null) {
            sb.append("    <Overview>").append(this.overview).append("</Overview>\n");
        }
        if (this.maxPlayers != null) {
            sb.append("    <MaxPlayers>").append(this.maxPlayers).append("</MaxPlayers>\n");
        }
        if (this.cooperative != null) {
            sb.append("    <Cooperative>").append(this.cooperative).append("</Cooperative>\n");
        }
        if (this.videoURL != null) {
            sb.append("    <VideoURL>").append(this.videoURL).append("</VideoURL>\n");
        }
        if (this.databaseID != null) {
            sb.append("    <DatabaseID>").append(this.databaseID).append("</DatabaseID>\n");
        }
        if (this.wikipediaURL != null) {
            sb.append("    <WikipediaURL>").append(this.wikipediaURL).append("</WikipediaURL>\n");
        }
        if (this.communityRating != null) {
            sb.append("    <CommunityRating>").append(this.communityRating).append("</CommunityRating>\n");
        }
        if (this.platform != null) {
            sb.append("    <Platform>").append(this.platform).append("</Platform>\n");
        }
        if (this.esrb != null) {
            sb.append("    <ESRB>").append(this.esrb).append("</ESRB>\n");
        }
        if (this.dos != null) {
            sb.append("    <DOS>").append(this.dos).append("</DOS>\n");
        }
        if (this.startupFile != null) {
            sb.append("    <StartupFile>").append(this.startupFile).append("</StartupFile>\n");
        }
        if (this.startupMD5 != null) {
            sb.append("    <StartupMD5>").append(this.startupMD5).append("</StartupMD5>\n");
        }
        if (this.startupParameters != null) {
            sb.append("    <StartupParameters>").append(this.startupParameters).append("</StartupParameters>\n");
        }
        if (this.setupFile != null) {
            sb.append("    <SetupFile>").append(this.setupFile).append("</SetupFile>\n");
        }
        if (this.setupMD5 != null) {
            sb.append("    <SetupMD5>").append(this.setupMD5).append("</SetupMD5>\n");
        }
        if (!this.getGenres().isEmpty()) {
            sb.append("    <Genres>");
            for (int i = 0; i < this.genres.size(); i++) {
                if (i > 0) sb.append(";");
                sb.append(" ").append(this.genres.get(i));
            }
            sb.append("    </Genres>\n");
        } else {
            sb.append("    <Genres />\n");
        }
        if (this.developer != null) {
            sb.append("    <Developer>").append(this.developer).append("</Developer>\n");
        }
        if (this.publisher != null) {
            sb.append("    <Publisher>").append(this.publisher).append("</Publisher>\n");
        }
        sb.append("  </Game>\n");
        return sb.toString();
    }
}
