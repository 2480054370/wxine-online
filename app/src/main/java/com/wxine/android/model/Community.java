package com.wxine.android.model;

import com.wxine.android.utils.HtmlUtil;
import com.wxine.android.utils.SubString;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class Community implements java.io.Serializable {
	private static final long serialVersionUID = 5255664133410825880L;
	private String id;
	private User user;
	private Syscate syscate;
	private Usercate usercate;
	private Community community;
	private String scope;
	private String friend;
	private String topic;
	private String locale;
	private String type;
	private String name;
	private String title;
	private String image;
	private String brief;
	private String tag;
	private String content;
	private Integer csort;
	private String status;
	private String userstatus;
	private Integer ilike;
	private Integer cmcount;
	private Integer sharecount;
	private Integer readcount;
	private String security;
	private String district;
	private String address;
	private Double latitude;
	private Double longitude;
	private String url;
	private String linkurl;
	private String meta_title;
	private String meta_key;
	private String meta_desc;
	private Timestamp ctime;
	private Timestamp utime;
	private User edituser;
	private Integer usercount;
	private Integer infocount;
	private Integer articlecount;
	private Integer eventcount;
	private Integer goodscount;
	private Integer jobcount;
	private Integer photocount;
	private Integer videocount;

	private Set<Cmuser> cmusers = new HashSet<Cmuser>(0);
	private Set<Image> images = new HashSet<Image>(0);

	private Set<String> scopes = new HashSet<String>(0);
	private Set<String> friends = new HashSet<String>(0);
	private Set<String> topics = new HashSet<String>(0);
	private Set<String> tags = new HashSet<String>(0);
	private String cleancontent;// 清除了所有标签

	private Set<Cmuser> mycmusers = new HashSet<Cmuser>();// 为当前用户过滤后的对象，只有一个
	private String mypermission;// 当前登录用户在群组中的权限
	private String mystatus;// 当前登录用户在群组中的状态
	private String myuserstatus;

	public Set<String> getScopes() {
		try {
			String[] array = scope.split(",");
			for (String a : array) {
				if (StringUtils.isNotBlank(a)) {
					scopes.add(StringUtils.trim(StringUtils.strip(a)));
				}
			}
		} catch (Exception e) {
		}
		return scopes;
	}

	public void setScopes(Set<String> scopes) {
		this.scopes = scopes;
	}

	public String existScope(String scope) {
		try {
			Set<String> set = getScopes();
			if (set.contains(scope)) {
				return "yes";
			} else {
				return "no";
			}
		} catch (Exception e) {
			return "no";
		}
	}

	public Set<String> getFriends() {
		try {
			String[] array = friend.split(",");
			for (String a : array) {
				if (StringUtils.isNotBlank(a)) {
					friends.add(StringUtils.trim(StringUtils.strip(a)));
				}
			}
		} catch (Exception e) {
		}
		return friends;
	}

	public void setFriends(Set<String> friends) {
		this.friends = friends;
	}

	public String existFriend(String friend) {
		try {
			Set<String> set = getFriends();
			if (set.contains(friend)) {
				return "yes";
			} else {
				return "no";
			}
		} catch (Exception e) {
			return "no";
		}
	}

	public Set<String> getTopics() {
		try {
			String[] array = topic.split(",");
			for (String a : array) {
				if (StringUtils.isNotBlank(a)) {
					topics.add(StringUtils.trim(StringUtils.strip(a)));
				}
			}
		} catch (Exception e) {
		}
		return topics;
	}

	public void setTopics(Set<String> topics) {
		this.topics = topics;
	}

	public String existTopic(String topic) {
		try {
			Set<String> set = getTopics();
			if (set.contains(topic)) {
				return "yes";
			} else {
				return "no";
			}
		} catch (Exception e) {
			return "no";
		}
	}

	public Set<String> getTags() {
		try {
			String[] array = tag.split(",");
			for (String a : array) {
				if (StringUtils.isNotBlank(a)) {
					tags.add(StringUtils.trim(StringUtils.strip(a)));
				}
			}
		} catch (Exception e) {
		}
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public String existTag(String tag) {
		try {
			Set<String> set = getTags();
			if (set.contains(tag)) {
				return "yes";
			} else {
				return "no";
			}
		} catch (Exception e) {
			return "no";
		}
	}

	public String getCleancontent() {
		try {
			cleancontent = HtmlUtil.cleanText(content);
		} catch (Exception e) {
		}
		return cleancontent;
	}

	public void setCleancontent(String cleantext) {
		this.cleancontent = cleantext;
	}

	public String getCleancontent(int length) {
		return getCleancontent(length, "");
	}

	public String getCleancontent(int length, String tag) {
		try {
			if (!StringUtils.isNotBlank(tag)) {
				tag = "...";
			}
			return SubString.substring(HtmlUtil.cleanText(content), length, tag);
		} catch (Exception e) {
		}
		return cleancontent;
	}

	public String clearhtml(String html) {
		return HtmlUtil.cleanHtml(html);
	}

	public String getMypermission() {
		return mypermission;
	}

	public void setMypermission(String mypermission) {
		this.mypermission = mypermission;
	}

	public String getMystatus() {
		return mystatus;
	}

	public void setMystatus(String mystatus) {
		this.mystatus = mystatus;
	}

	public String getMyuserstatus() {
		return myuserstatus;
	}

	public void setMyuserstatus(String myuserstatus) {
		this.myuserstatus = myuserstatus;
	}

	/** default constructor */
	public Community() {
	}

	/** minimal constructor */
	public Community(String id, String code, String security, String name, String path, String status,
			String certlicense, Timestamp ctime) {
		this.id = id;
		this.security = security;
		this.name = name;
		this.status = status;
		this.ctime = ctime;
	}

	/** full constructor */
	public Community(String id, User user, Syscate syscate, Usercate usercate, Community community,
					 String scope, String friend, String topic, String locale, String type, String name, String title,
					 String image, String brief, String tag, String content, Integer csort, String status,
					 String userstatus, Integer ilike, Integer cmcount, Integer sharecount, Integer readcount,
					 String security, String district, String address, Double latitude, Double longitude, String url,
					 String linkurl, String meta_title, String meta_key, String meta_desc, Timestamp ctime,
					 Timestamp utime, User edituser, Integer usercount, Integer infocount, Integer articlecount,
					 Integer eventcount, Integer goodscount, Integer jobcount, Integer photocount, Integer videocount,
					 Set<Cmuser> cmusers, Set<Image> images) {
		this.id = id;
		this.user = user;
		this.syscate = syscate;
		this.usercate = usercate;
		this.community = community;
		this.scope = scope;
		this.friend = friend;
		this.topic = topic;
		this.locale = locale;
		this.type = type;
		this.name = name;
		this.title = title;
		this.image = image;
		this.brief = brief;
		this.tag = tag;
		this.content = content;
		this.csort = csort;
		this.status = status;
		this.userstatus = userstatus;
		this.ilike = ilike;
		this.cmcount = cmcount;
		this.sharecount = sharecount;
		this.readcount = readcount;
		this.security = security;
		this.district = district;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.url = url;
		this.linkurl = linkurl;
		this.meta_title = meta_title;
		this.meta_key = meta_key;
		this.meta_desc = meta_desc;
		this.ctime = ctime;
		this.utime = utime;
		this.edituser = edituser;
		this.usercount = usercount;
		this.infocount = infocount;
		this.articlecount = articlecount;
		this.eventcount = eventcount;
		this.goodscount = goodscount;
		this.jobcount = jobcount;
		this.photocount = photocount;
		this.videocount = videocount;
		this.cmusers = cmusers;
		this.images = images;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Syscate getSyscate() {
		return syscate;
	}

	public void setSyscate(Syscate syscate) {
		this.syscate = syscate;
	}

	public Usercate getUsercate() {
		return usercate;
	}

	public void setUsercate(Usercate usercate) {
		this.usercate = usercate;
	}

	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getFriend() {
		return friend;
	}

	public void setFriend(String friend) {
		this.friend = friend;
	}

	public String getTopic() {
		return this.topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getLocale() {
		return this.locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		if (!StringUtils.isNotBlank(image)) {// 如果没有封面图，取一张图
			try {
				this.image = images.iterator().next().getUrl();
			} catch (Exception e) {
			}
		}
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getBrief() {
		return getBrief(100);
	}

	public String getBrief(int length) {
		return getBrief(length, "");
	}

	public String getBrief(int length, String tag) {
		try {
			if (StringUtils.isNotBlank(title)) {
				return SubString.substring(HtmlUtil.cleanText(title + "," + content), length, tag);
			} else {
				return SubString.substring(HtmlUtil.cleanText(content), length, tag);
			}
		} catch (Exception e) {
		}
		return this.brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCsort() {
		return this.csort;
	}

	public void setCsort(Integer csort) {
		this.csort = csort;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserstatus() {
		return userstatus;
	}

	public void setUserstatus(String userstatus) {
		this.userstatus = userstatus;
	}

	public Integer getIlike() {
		return this.ilike;
	}

	public void setIlike(Integer ilike) {
		this.ilike = ilike;
	}

	public Integer getCmcount() {
		return this.cmcount;
	}

	public void setCmcount(Integer cmcount) {
		this.cmcount = cmcount;
	}

	public Integer getSharecount() {
		return sharecount;
	}

	public void setSharecount(Integer sharecount) {
		this.sharecount = sharecount;
	}

	public Integer getReadcount() {
		return readcount;
	}

	public void setReadcount(Integer readcount) {
		this.readcount = readcount;
	}

	public String getSecurity() {
		return security;
	}

	public void setSecurity(String security) {
		this.security = security;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLinkurl() {
		return linkurl;
	}

	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}

	public String getMeta_title() {
		return meta_title;
	}

	public void setMeta_title(String meta_title) {
		this.meta_title = meta_title;
	}

	public String getMeta_key() {
		return meta_key;
	}

	public void setMeta_key(String meta_key) {
		this.meta_key = meta_key;
	}

	public String getMeta_desc() {
		return meta_desc;
	}

	public void setMeta_desc(String meta_desc) {
		this.meta_desc = meta_desc;
	}

	public Timestamp getCtime() {
		return this.ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public Timestamp getUtime() {
		return utime;
	}

	public void setUtime(Timestamp utime) {
		this.utime = utime;
	}

	public User getEdituser() {
		return edituser;
	}

	public void setEdituser(User edituser) {
		this.edituser = edituser;
	}

	public Integer getUsercount() {
		return usercount;
	}

	public void setUsercount(Integer usercount) {
		this.usercount = usercount;
	}

	public Integer getInfocount() {
		return infocount;
	}

	public void setInfocount(Integer infocount) {
		this.infocount = infocount;
	}

	public Integer getArticlecount() {
		return articlecount;
	}

	public void setArticlecount(Integer articlecount) {
		this.articlecount = articlecount;
	}
	
	public Integer getEventcount() {
		return eventcount;
	}

	public void setEventcount(Integer eventcount) {
		this.eventcount = eventcount;
	}

	public Integer getGoodscount() {
		return goodscount;
	}

	public void setGoodscount(Integer goodscount) {
		this.goodscount = goodscount;
	}

	public Integer getJobcount() {
		return jobcount;
	}

	public void setJobcount(Integer jobcount) {
		this.jobcount = jobcount;
	}

	public Integer getPhotocount() {
		return photocount;
	}

	public void setPhotocount(Integer photocount) {
		this.photocount = photocount;
	}

	public Integer getVideocount() {
		return videocount;
	}

	public void setVideocount(Integer videocount) {
		this.videocount = videocount;
	}

	public Set<Cmuser> getCmusers() {
		return cmusers;
	}

	public void setCmusers(Set<Cmuser> cmusers) {
		this.cmusers = cmusers;
	}

	public Set<Image> getImages() {
		return this.images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}

	public Set<Cmuser> getMycmusers() {
		return mycmusers;
	}

	public void setMycmusers(Set<Cmuser> mycmusers) {
		this.mycmusers = mycmusers;
	}
}