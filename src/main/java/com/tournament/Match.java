package com.tournament;

import java.util.Date;

public class Match {

    private String matchId;
    private Team teamOneSnapshot;
    private Team teamTwoSnapshot;
    private Date startDate;
    private String city;
    private Ground ground;
    private Integer maxOversPerBowler;
    private String matchType;
    private String matchStatus;
    private String tournamentCategory;
    private Integer totalInnings;
    private String tossCaller;
    private String tossWinner;
    private String tossCall;
    private String tossResult;
    private Boolean isDraw = false;
    private Boolean isNoResult = false;
    private String streamKey1;
    private String streamKey2;
    private String streamUrl;
    private String attachmentUrl1;
    private String attachmentUrl2;
    private String mediaLiveChannel;
    private String mediaPackageChannel;

    private String slotInfo;

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public Team getTeamOneSnapshot() {
        return teamOneSnapshot;
    }

    public void setTeamOneSnapshot(Team teamOneSnapshot) {
        this.teamOneSnapshot = teamOneSnapshot;
    }

    public Team getTeamTwoSnapshot() {
        return teamTwoSnapshot;
    }

    public void setTeamTwoSnapshot(Team teamTwoSnapshot) {
        this.teamTwoSnapshot = teamTwoSnapshot;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Ground getGround() {
        return ground;
    }

    public void setGround(Ground ground) {
        this.ground = ground;
    }

    public Integer getMaxOversPerBowler() {
        return maxOversPerBowler;
    }

    public void setMaxOversPerBowler(Integer maxOversPerBowler) {
        this.maxOversPerBowler = maxOversPerBowler;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public String getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }

    public String getTournamentCategory() {
        return tournamentCategory;
    }

    public void setTournamentCategory(String tournamentCategory) {
        this.tournamentCategory = tournamentCategory;
    }

    public Integer getTotalInnings() {
        return totalInnings;
    }

    public void setTotalInnings(Integer totalInnings) {
        this.totalInnings = totalInnings;
    }

    public String getTossCaller() {
        return tossCaller;
    }

    public void setTossCaller(String tossCaller) {
        this.tossCaller = tossCaller;
    }

    public String getTossWinner() {
        return tossWinner;
    }

    public void setTossWinner(String tossWinner) {
        this.tossWinner = tossWinner;
    }

    public String getTossCall() {
        return tossCall;
    }

    public void setTossCall(String tossCall) {
        this.tossCall = tossCall;
    }

    public String getTossResult() {
        return tossResult;
    }

    public void setTossResult(String tossResult) {
        this.tossResult = tossResult;
    }

    public Boolean getDraw() {
        return isDraw;
    }

    public void setDraw(Boolean draw) {
        isDraw = draw;
    }

    public Boolean getNoResult() {
        return isNoResult;
    }

    public void setNoResult(Boolean noResult) {
        isNoResult = noResult;
    }

    public String getStreamKey1() {
        return streamKey1;
    }

    public void setStreamKey1(String streamKey1) {
        this.streamKey1 = streamKey1;
    }

    public String getStreamKey2() {
        return streamKey2;
    }

    public void setStreamKey2(String streamKey2) {
        this.streamKey2 = streamKey2;
    }

    public String getStreamUrl() {
        return streamUrl;
    }

    public void setStreamUrl(String streamUrl) {
        this.streamUrl = streamUrl;
    }

    public String getAttachmentUrl1() {
        return attachmentUrl1;
    }

    public void setAttachmentUrl1(String attachmentUrl1) {
        this.attachmentUrl1 = attachmentUrl1;
    }

    public String getAttachmentUrl2() {
        return attachmentUrl2;
    }

    public void setAttachmentUrl2(String attachmentUrl2) {
        this.attachmentUrl2 = attachmentUrl2;
    }

    public String getMediaLiveChannel() {
        return mediaLiveChannel;
    }

    public void setMediaLiveChannel(String mediaLiveChannel) {
        this.mediaLiveChannel = mediaLiveChannel;
    }

    public String getMediaPackageChannel() {
        return mediaPackageChannel;
    }

    public void setMediaPackageChannel(String mediaPackageChannel) {
        this.mediaPackageChannel = mediaPackageChannel;
    }

    public String getSlotInfo() {
        return slotInfo;
    }

    public void setSlotInfo(String slotInfo) {
        this.slotInfo = slotInfo;
    }

    @Override
    public String toString() {
        return  teamOneSnapshot.getTeamName() + " " + teamTwoSnapshot.getTeamName() + " " + MyDateParser.toString(startDate) + " " +ground.getGroundName() +" "+ slotInfo;
    }
}
