package com.xiaoliwu.yll.json;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Administrator on 2015/10/26.
 */
public class StrategyBean {
    /**
     * code : 200
     * data : {"collections":[{"banner_image_url":"http://img03.liwushuo.com/image/151025/kps9qalnb.jpg-w300","cover_image_url":"http://img01.liwushuo.com/image/151025/djk19w30f.jpg-w720","created_at":1445764821,"id":149,"posts_count":6,"status":0,"subtitle":"除了喝热水，别忘了还有这些","title":"大姨妈驾到","updated_at":1445764821},{"banner_image_url":"http://img03.liwushuo.com/image/151019/r0atxmful.jpg-w300","cover_image_url":"http://img02.liwushuo.com/image/151019/8fe266vsu.jpg-w720","created_at":1445227932,"id":147,"posts_count":10,"status":0,"subtitle":"天猫国际场","title":"双11什么值得买","updated_at":1445227932},{"banner_image_url":"http://img03.liwushuo.com/image/151019/k4hhlnffz.jpg-w300","cover_image_url":"http://img02.liwushuo.com/image/151019/frx15qfo6.jpg-w720","created_at":1445227614,"id":146,"posts_count":12,"status":0,"subtitle":"预售篇","title":"双11什么值得买第一弹","updated_at":1445227614},{"banner_image_url":"http://img01.liwushuo.com/image/151021/n3cm4muwq.jpg-w300","cover_image_url":"http://img01.liwushuo.com/image/151025/wijp6yl3x.jpg-w720","created_at":1445219020,"id":148,"posts_count":5,"status":0,"subtitle":"单身汪勿戳","title":"\b秀恩爱的正确姿势","updated_at":1445391820},{"banner_image_url":"http://img03.liwushuo.com/image/151016/u9zqsujzo.jpg-w300","cover_image_url":"http://img03.liwushuo.com/image/151016/f9ikok9ug.jpg-w720","created_at":1444994624,"id":145,"posts_count":4,"status":0,"subtitle":"不贴秋膘也能过个暖冬","title":"御寒神器","updated_at":1444994624},{"banner_image_url":"http://img01.liwushuo.com/image/151016/kxcyof7do.jpg-w300","cover_image_url":"http://img01.liwushuo.com/image/151016/44sukae3e.jpg-w720","created_at":1444994238,"id":144,"posts_count":4,"status":0,"subtitle":null,"title":"吃货の人生法则","updated_at":1444994238},{"banner_image_url":"http://img02.liwushuo.com/image/151013/abj4kcs8r.jpg-w300","cover_image_url":"http://img03.liwushuo.com/image/151013/frbk2vg3l.jpg-w720","created_at":1444741381,"id":143,"posts_count":5,"status":0,"subtitle":"御秋寒の好物 温暖针织衫","title":"针织衫单品","updated_at":1444741381},{"banner_image_url":"http://img01.liwushuo.com/image/151013/2rb5cjr68.jpg-w300","cover_image_url":"http://img03.liwushuo.com/image/151013/u3gxigkd5.jpg-w720","created_at":1444736912,"id":142,"posts_count":4,"status":0,"subtitle":"温暖你的心窝","title":"美貌床品 ","updated_at":1444736912},{"banner_image_url":"http://img01.liwushuo.com/image/151013/dd4mjjcem.jpg-w300","cover_image_url":"http://img02.liwushuo.com/image/151013/wy339by3r.jpg-w720","created_at":1444736110,"id":141,"posts_count":7,"status":0,"subtitle":"万能礼物！不会送礼物星人通关指南","title":"万能礼物清单","updated_at":1444736110},{"banner_image_url":"http://img03.liwushuo.com/image/151013/z4mtohc2k.jpg-w300","cover_image_url":"http://img01.liwushuo.com/image/151013/8mtoeh6nl.jpg-w720","created_at":1444699210,"id":140,"posts_count":4,"status":0,"subtitle":"换季啦，千万别说你的护肤品还没换！","title":"换季护肤品","updated_at":1444699210}],"paging":{"next_url":"http://api.liwushuo.com/v2/collections?limit=10&offset=10"}}
     * message : OK
     */

    private int code;
    private DataEntity data;
    private String message;

    public static StrategyBean objectFromData(String str) {

        return new Gson().fromJson(str, StrategyBean.class);
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public DataEntity getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public static class DataEntity {
        /**
         * collections : [{"banner_image_url":"http://img03.liwushuo.com/image/151025/kps9qalnb.jpg-w300","cover_image_url":"http://img01.liwushuo.com/image/151025/djk19w30f.jpg-w720","created_at":1445764821,"id":149,"posts_count":6,"status":0,"subtitle":"除了喝热水，别忘了还有这些","title":"大姨妈驾到","updated_at":1445764821},{"banner_image_url":"http://img03.liwushuo.com/image/151019/r0atxmful.jpg-w300","cover_image_url":"http://img02.liwushuo.com/image/151019/8fe266vsu.jpg-w720","created_at":1445227932,"id":147,"posts_count":10,"status":0,"subtitle":"天猫国际场","title":"双11什么值得买","updated_at":1445227932},{"banner_image_url":"http://img03.liwushuo.com/image/151019/k4hhlnffz.jpg-w300","cover_image_url":"http://img02.liwushuo.com/image/151019/frx15qfo6.jpg-w720","created_at":1445227614,"id":146,"posts_count":12,"status":0,"subtitle":"预售篇","title":"双11什么值得买第一弹","updated_at":1445227614},{"banner_image_url":"http://img01.liwushuo.com/image/151021/n3cm4muwq.jpg-w300","cover_image_url":"http://img01.liwushuo.com/image/151025/wijp6yl3x.jpg-w720","created_at":1445219020,"id":148,"posts_count":5,"status":0,"subtitle":"单身汪勿戳","title":"\b秀恩爱的正确姿势","updated_at":1445391820},{"banner_image_url":"http://img03.liwushuo.com/image/151016/u9zqsujzo.jpg-w300","cover_image_url":"http://img03.liwushuo.com/image/151016/f9ikok9ug.jpg-w720","created_at":1444994624,"id":145,"posts_count":4,"status":0,"subtitle":"不贴秋膘也能过个暖冬","title":"御寒神器","updated_at":1444994624},{"banner_image_url":"http://img01.liwushuo.com/image/151016/kxcyof7do.jpg-w300","cover_image_url":"http://img01.liwushuo.com/image/151016/44sukae3e.jpg-w720","created_at":1444994238,"id":144,"posts_count":4,"status":0,"subtitle":null,"title":"吃货の人生法则","updated_at":1444994238},{"banner_image_url":"http://img02.liwushuo.com/image/151013/abj4kcs8r.jpg-w300","cover_image_url":"http://img03.liwushuo.com/image/151013/frbk2vg3l.jpg-w720","created_at":1444741381,"id":143,"posts_count":5,"status":0,"subtitle":"御秋寒の好物 温暖针织衫","title":"针织衫单品","updated_at":1444741381},{"banner_image_url":"http://img01.liwushuo.com/image/151013/2rb5cjr68.jpg-w300","cover_image_url":"http://img03.liwushuo.com/image/151013/u3gxigkd5.jpg-w720","created_at":1444736912,"id":142,"posts_count":4,"status":0,"subtitle":"温暖你的心窝","title":"美貌床品 ","updated_at":1444736912},{"banner_image_url":"http://img01.liwushuo.com/image/151013/dd4mjjcem.jpg-w300","cover_image_url":"http://img02.liwushuo.com/image/151013/wy339by3r.jpg-w720","created_at":1444736110,"id":141,"posts_count":7,"status":0,"subtitle":"万能礼物！不会送礼物星人通关指南","title":"万能礼物清单","updated_at":1444736110},{"banner_image_url":"http://img03.liwushuo.com/image/151013/z4mtohc2k.jpg-w300","cover_image_url":"http://img01.liwushuo.com/image/151013/8mtoeh6nl.jpg-w720","created_at":1444699210,"id":140,"posts_count":4,"status":0,"subtitle":"换季啦，千万别说你的护肤品还没换！","title":"换季护肤品","updated_at":1444699210}]
         * paging : {"next_url":"http://api.liwushuo.com/v2/collections?limit=10&offset=10"}
         */

        private PagingEntity paging;
        private List<CollectionsEntity> collections;

        public static DataEntity objectFromData(String str) {

            return new Gson().fromJson(str, DataEntity.class);
        }

        public void setPaging(PagingEntity paging) {
            this.paging = paging;
        }

        public void setCollections(List<CollectionsEntity> collections) {
            this.collections = collections;
        }

        public PagingEntity getPaging() {
            return paging;
        }

        public List<CollectionsEntity> getCollections() {
            return collections;
        }

        public static class PagingEntity {
            /**
             * next_url : http://api.liwushuo.com/v2/collections?limit=10&offset=10
             */

            private String next_url;

            public static PagingEntity objectFromData(String str) {

                return new Gson().fromJson(str, PagingEntity.class);
            }

            public void setNext_url(String next_url) {
                this.next_url = next_url;
            }

            public String getNext_url() {
                return next_url;
            }
        }

        public static class CollectionsEntity {
            /**
             * banner_image_url : http://img03.liwushuo.com/image/151025/kps9qalnb.jpg-w300
             * cover_image_url : http://img01.liwushuo.com/image/151025/djk19w30f.jpg-w720
             * created_at : 1445764821
             * id : 149
             * posts_count : 6
             * status : 0
             * subtitle : 除了喝热水，别忘了还有这些
             * title : 大姨妈驾到
             * updated_at : 1445764821
             */

            private String banner_image_url;
            private String cover_image_url;
            private int created_at;
            private int id;
            private int posts_count;
            private int status;
            private String subtitle;
            private String title;
            private int updated_at;

            public static CollectionsEntity objectFromData(String str) {

                return new Gson().fromJson(str, CollectionsEntity.class);
            }

            public void setBanner_image_url(String banner_image_url) {
                this.banner_image_url = banner_image_url;
            }

            public void setCover_image_url(String cover_image_url) {
                this.cover_image_url = cover_image_url;
            }

            public void setCreated_at(int created_at) {
                this.created_at = created_at;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setPosts_count(int posts_count) {
                this.posts_count = posts_count;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setUpdated_at(int updated_at) {
                this.updated_at = updated_at;
            }

            public String getBanner_image_url() {
                return banner_image_url;
            }

            public String getCover_image_url() {
                return cover_image_url;
            }

            public int getCreated_at() {
                return created_at;
            }

            public int getId() {
                return id;
            }

            public int getPosts_count() {
                return posts_count;
            }

            public int getStatus() {
                return status;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public String getTitle() {
                return title;
            }

            public int getUpdated_at() {
                return updated_at;
            }
        }
    }
}
