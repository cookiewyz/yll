package com.xiaoliwu.yll.json;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Administrator on 2015/10/20.
 */
public class HomeBean {


    /**
     * code : 200
     * data : {"banners":[{"channel":"all","id":352,"image_url":"http://img02.liwushuo.com/image/151019/0ane259zs.jpg-w720","order":4,"status":0,"target_id":null,"target_url":"http://redirect.liwushuo.com/j/olay1019","type":"url"},{"channel":"all","id":355,"image_url":"http://img03.liwushuo.com/image/151020/eopvdzzib.jpg-w720","order":4,"status":0,"target_id":null,"target_url":"liwushuo:///page?type=shop_item&item_id=100171","type":"url"},{"channel":"all","id":353,"image_url":"http://img02.liwushuo.com/image/151019/g53u9jtwu.jpg-w720","order":3,"status":0,"target":{"banner_image_url":"http://img03.liwushuo.com/image/151019/k4hhlnffz.jpg-w300","cover_image_url":"http://img02.liwushuo.com/image/151019/frx15qfo6.jpg-w720","created_at":1445227614,"id":146,"posts_count":9,"status":0,"subtitle":"预售篇","title":"双11什么值得买第一弹","updated_at":1445227614},"target_id":146,"target_url":"","type":"collection"},{"channel":"all","id":354,"image_url":"http://img02.liwushuo.com/image/151019/kfzwm0ivf.jpg-w720","order":3,"status":0,"target":{"banner_image_url":"http://img01.liwushuo.com/image/151019/r0atxmful.jpg-w300","cover_image_url":"http://img01.liwushuo.com/image/151019/8fe266vsu.jpg-w720","created_at":1445227932,"id":147,"posts_count":7,"status":0,"subtitle":"天猫国际场","title":"双11什么值得买","updated_at":1445227932},"target_id":147,"target_url":"","type":"collection"},{"channel":"all","id":345,"image_url":"http://img01.liwushuo.com/image/151013/h4h7lqhrp.jpg-w720","order":1,"status":0,"target":{"banner_image_url":"http://img03.liwushuo.com/image/151013/dd4mjjcem.jpg-w300","cover_image_url":"http://img01.liwushuo.com/image/151013/wy339by3r.jpg-w720","created_at":1444736110,"id":141,"posts_count":7,"status":0,"subtitle":"万能礼物！不会送礼物星人通关指南","title":"万能礼物清单","updated_at":1444736110},"target_id":141,"target_url":"","type":"collection"},{"channel":"all","id":348,"image_url":"http://img02.liwushuo.com/image/151016/6vxrf6et4.jpg-w720","order":1,"status":0,"target":{"banner_image_url":"http://img01.liwushuo.com/image/151016/kxcyof7do.jpg-w300","cover_image_url":"http://img03.liwushuo.com/image/151016/44sukae3e.jpg-w720","created_at":1444994238,"id":144,"posts_count":4,"status":0,"subtitle":null,"title":"吃货の人生法则","updated_at":1444994238},"target_id":144,"target_url":"","type":"collection"},{"channel":"all","id":340,"image_url":"http://img03.liwushuo.com/image/151013/n2sl22gsn.jpg-w720","order":0,"status":0,"target":{"banner_image_url":"http://img03.liwushuo.com/image/151013/z4mtohc2k.jpg-w300","cover_image_url":"http://img03.liwushuo.com/image/151013/8mtoeh6nl.jpg-w720","created_at":1444699210,"id":140,"posts_count":4,"status":0,"subtitle":"换季啦，千万别说你的护肤品还没换！","title":"换季护肤品","updated_at":1444699210},"target_id":140,"target_url":"","type":"collection"},{"channel":"all","id":311,"image_url":"http://img03.liwushuo.com/image/151006/96ylxtfua.jpg-w720","order":-1,"status":0,"target":{"banner_image_url":"http://img02.liwushuo.com/image/151006/2gbqdx01r.jpg-w300","cover_image_url":"http://img01.liwushuo.com/image/151006/4m9ajqep1.jpg-w720","created_at":1444103256,"id":139,"posts_count":6,"status":0,"subtitle":"没人能够拒绝的zakka风家居","title":"zakka风家居","updated_at":1444103256},"target_id":139,"target_url":"","type":"collection"}]}
     * message : OK
     */

    private int code;
    private DataEntity data;
    private String message;

    public static HomeBean objectFromData(String str) {

        return new Gson().fromJson(str, HomeBean.class);
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
         * banners : [{"channel":"all","id":352,"image_url":"http://img02.liwushuo.com/image/151019/0ane259zs.jpg-w720","order":4,"status":0,"target_id":null,"target_url":"http://redirect.liwushuo.com/j/olay1019","type":"url"},{"channel":"all","id":355,"image_url":"http://img03.liwushuo.com/image/151020/eopvdzzib.jpg-w720","order":4,"status":0,"target_id":null,"target_url":"liwushuo:///page?type=shop_item&item_id=100171","type":"url"},{"channel":"all","id":353,"image_url":"http://img02.liwushuo.com/image/151019/g53u9jtwu.jpg-w720","order":3,"status":0,"target":{"banner_image_url":"http://img03.liwushuo.com/image/151019/k4hhlnffz.jpg-w300","cover_image_url":"http://img02.liwushuo.com/image/151019/frx15qfo6.jpg-w720","created_at":1445227614,"id":146,"posts_count":9,"status":0,"subtitle":"预售篇","title":"双11什么值得买第一弹","updated_at":1445227614},"target_id":146,"target_url":"","type":"collection"},{"channel":"all","id":354,"image_url":"http://img02.liwushuo.com/image/151019/kfzwm0ivf.jpg-w720","order":3,"status":0,"target":{"banner_image_url":"http://img01.liwushuo.com/image/151019/r0atxmful.jpg-w300","cover_image_url":"http://img01.liwushuo.com/image/151019/8fe266vsu.jpg-w720","created_at":1445227932,"id":147,"posts_count":7,"status":0,"subtitle":"天猫国际场","title":"双11什么值得买","updated_at":1445227932},"target_id":147,"target_url":"","type":"collection"},{"channel":"all","id":345,"image_url":"http://img01.liwushuo.com/image/151013/h4h7lqhrp.jpg-w720","order":1,"status":0,"target":{"banner_image_url":"http://img03.liwushuo.com/image/151013/dd4mjjcem.jpg-w300","cover_image_url":"http://img01.liwushuo.com/image/151013/wy339by3r.jpg-w720","created_at":1444736110,"id":141,"posts_count":7,"status":0,"subtitle":"万能礼物！不会送礼物星人通关指南","title":"万能礼物清单","updated_at":1444736110},"target_id":141,"target_url":"","type":"collection"},{"channel":"all","id":348,"image_url":"http://img02.liwushuo.com/image/151016/6vxrf6et4.jpg-w720","order":1,"status":0,"target":{"banner_image_url":"http://img01.liwushuo.com/image/151016/kxcyof7do.jpg-w300","cover_image_url":"http://img03.liwushuo.com/image/151016/44sukae3e.jpg-w720","created_at":1444994238,"id":144,"posts_count":4,"status":0,"subtitle":null,"title":"吃货の人生法则","updated_at":1444994238},"target_id":144,"target_url":"","type":"collection"},{"channel":"all","id":340,"image_url":"http://img03.liwushuo.com/image/151013/n2sl22gsn.jpg-w720","order":0,"status":0,"target":{"banner_image_url":"http://img03.liwushuo.com/image/151013/z4mtohc2k.jpg-w300","cover_image_url":"http://img03.liwushuo.com/image/151013/8mtoeh6nl.jpg-w720","created_at":1444699210,"id":140,"posts_count":4,"status":0,"subtitle":"换季啦，千万别说你的护肤品还没换！","title":"换季护肤品","updated_at":1444699210},"target_id":140,"target_url":"","type":"collection"},{"channel":"all","id":311,"image_url":"http://img03.liwushuo.com/image/151006/96ylxtfua.jpg-w720","order":-1,"status":0,"target":{"banner_image_url":"http://img02.liwushuo.com/image/151006/2gbqdx01r.jpg-w300","cover_image_url":"http://img01.liwushuo.com/image/151006/4m9ajqep1.jpg-w720","created_at":1444103256,"id":139,"posts_count":6,"status":0,"subtitle":"没人能够拒绝的zakka风家居","title":"zakka风家居","updated_at":1444103256},"target_id":139,"target_url":"","type":"collection"}]
         */

        private List<BannersEntity> banners;

        public static DataEntity objectFromData(String str) {

            return new Gson().fromJson(str, DataEntity.class);
        }

        public void setBanners(List<BannersEntity> banners) {
            this.banners = banners;
        }

        public List<BannersEntity> getBanners() {
            return banners;
        }

        public static class BannersEntity {
            /**
             * channel : all
             * id : 352
             * image_url : http://img02.liwushuo.com/image/151019/0ane259zs.jpg-w720
             * order : 4
             * status : 0
             * target_id : null
             * target_url : http://redirect.liwushuo.com/j/olay1019
             * type : url
             */

            private String channel;
            private int id;
            private String image_url;
            private int order;
            private int status;
            private Object target_id;
            private String target_url;
            private String type;

            public static BannersEntity objectFromData(String str) {

                return new Gson().fromJson(str, BannersEntity.class);
            }

            public void setChannel(String channel) {
                this.channel = channel;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public void setOrder(int order) {
                this.order = order;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public void setTarget_id(Object target_id) {
                this.target_id = target_id;
            }

            public void setTarget_url(String target_url) {
                this.target_url = target_url;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getChannel() {
                return channel;
            }

            public int getId() {
                return id;
            }

            public String getImage_url() {
                return image_url;
            }

            public int getOrder() {
                return order;
            }

            public int getStatus() {
                return status;
            }

            public Object getTarget_id() {
                return target_id;
            }

            public String getTarget_url() {
                return target_url;
            }

            public String getType() {
                return type;
            }
        }
    }
}
