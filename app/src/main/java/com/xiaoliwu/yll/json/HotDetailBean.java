package com.xiaoliwu.yll.json;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Administrator on 2015/10/21.
 */
public class HotDetailBean {


    /**
     * code : 200
     * data : {"brand_id":null,"brand_order":null,"category_id":3,"comments_count":2,"cover_image_url":"http://img03.taobaocdn.com/bao/uploaded/i3/18363024574885733/T1IzloFh0gXXXXXXXX_!!0-item_pic.jpg_480x480q60.jpg","created_at":1416364298,"description":"夜深了，你是否想起了许多尚待完成的梦想，送给你这盏复古的地球仪灯，愿这世界陪你安稳入眠。","detail_html":"<!doctype html>\n<html>\n<head>\n  <meta charset=\"UTF-8\">\n  <meta name=\"renderer\" content=\"webkit\">\n  <meta name=\"format-detection\" content=\"telephone=no\">\n  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge\">\n  <meta name=\"apple-mobile-web-app-capable\" content=\"yes\">\n  <meta name=\"apple-mobile-web-app-status-bar-style\" content=\"black\">\n  <meta id=\"vp\" name=\"viewport\" content=\"width=device-width, user-scalable=no,maximum-scale=1.0,initial-scale=1\">\n\t<title>商品详情<\/title>\n  <link rel=\"stylesheet\" type=\"text/css\" href=\"http://static.liwushuo.com/static/web/apps/liwushuo/css/item_72d7c80.css\">\n\n\n<\/head>\n<body>\n  <p><img src=\"http://gw.alicdn.com/imgextra/i1/321318363/T2posDXBVXXXXXXXXX-321318363.jpg_620x10000q60.jpg\" alt=\"\" /><img src=\"http://gw.alicdn.com/imgextra/i1/321318363/T2krICXstaXXXXXXXX-321318363.jpg_620x10000q60.jpg\" alt=\"\" /><img src=\"http://gw.alicdn.com/imgextra/i1/321318363/T2f97HXqxXXXXXXXXX-321318363.jpg_620x10000q60.jpg\" alt=\"\" /><img src=\"http://gw.alicdn.com/imgextra/i4/321318363/T2YuEIXrRXXXXXXXXX-321318363.jpg_620x10000q60.jpg\" alt=\"\" /><img src=\"http://gw.alicdn.com/imgextra/i3/321318363/T2yJgCXxBaXXXXXXXX-321318363.jpg_620x10000q60.jpg\" alt=\"\" /><img src=\"http://gw.alicdn.com/imgextra/i4/321318363/T2hc3EXDVXXXXXXXXX-321318363.jpg_620x10000q60.jpg\" alt=\"\" /><img src=\"http://gw.alicdn.com/imgextra/i3/321318363/T2dloGXyRXXXXXXXXX-321318363.jpg_620x10000q60.jpg\" alt=\"\" /><img src=\"http://gw.alicdn.com/imgextra/i1/321318363/T2JDADXpBaXXXXXXXX-321318363.jpg_620x10000q60.jpg\" alt=\"\" /><img src=\"http://gw.alicdn.com/imgextra/i4/321318363/T2i0cEXD0XXXXXXXXX-321318363.jpg_620x10000q60.jpg\" alt=\"\" /><img src=\"http://gw.alicdn.com/imgextra/i3/321318363/T2zjADXEVXXXXXXXXX-321318363.jpg_620x10000q60.jpg\" alt=\"\" /><img src=\"http://gw.alicdn.com/imgextra/i3/321318363/T2bNZHXvVXXXXXXXXX-321318363.jpg_620x10000q60.jpg\" alt=\"\" /><img src=\"http://gw.alicdn.com/imgextra/i1/321318363/T2P9MDXpVaXXXXXXXX-321318363.jpg_620x10000q60.jpg\" alt=\"\" /><img src=\"http://gw.alicdn.com/imgextra/i4/321318363/T2qP.DXqxaXXXXXXXX-321318363.jpg_620x10000q60.jpg\" alt=\"\" /><\/p>\n<\/body>\n<\/html>","editor_id":1024,"favorited":false,"favorites_count":3258,"id":1001052,"image_urls":["http://img03.taobaocdn.com/bao/uploaded/i3/18363024574885733/T1IzloFh0gXXXXXXXX_!!0-item_pic.jpg_480x480q60.jpg","http://img04.taobaocdn.com/imgextra/i4/321318363/TB2pNIlaFXXXXa4XpXXXXXXXXXX_!!321318363.jpg_480x480q60.jpg"],"liked":false,"likes_count":3258,"name":"台湾设计 地球仪灯","post_ids":["120"],"price":"292.50","purchase_id":null,"purchase_status":3,"purchase_type":1,"purchase_url":"http://s.click.taobao.com/t?sche=liwushuo&e=m%3D2%26s%3DQrnWB8RwlWUcQipKwQzePOeEDrYVVa64Qih%2F7PxfOKS5VBFTL4hn2XyJgS%2FiZXC2lg6AtVBcXjxsOJRC4t67ybSnlAIVMwbDnEVXIc3MIL6QRwrNct1IPOwi13EI0KUc3%2BJgygY0jrsEv5zvzTw268YOae24fhW0","shares_count":127,"source":{"button_title":"去淘宝购买","name":"淘宝","page_title":"商品详情"},"subcategory_id":21,"updated_at":1416364298,"url":"http://www.liwushuo.com/items/1001052"}
     * message : OK
     */

    private int code;
    private DataEntity data;
    private String message;

    public static HotDetailBean objectFromData(String str) {

        return new Gson().fromJson(str, HotDetailBean.class);
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
         * brand_id : null
         * brand_order : null
         * category_id : 3
         * comments_count : 2
         * cover_image_url : http://img03.taobaocdn.com/bao/uploaded/i3/18363024574885733/T1IzloFh0gXXXXXXXX_!!0-item_pic.jpg_480x480q60.jpg
         * created_at : 1416364298
         * description : 夜深了，你是否想起了许多尚待完成的梦想，送给你这盏复古的地球仪灯，愿这世界陪你安稳入眠。
         * detail_html : <!doctype html>
         <html>
         <head>
         <meta charset="UTF-8">
         <meta name="renderer" content="webkit">
         <meta name="format-detection" content="telephone=no">
         <meta http-equiv="X-UA-Compatible" content="IE=Edge">
         <meta name="apple-mobile-web-app-capable" content="yes">
         <meta name="apple-mobile-web-app-status-bar-style" content="black">
         <meta id="vp" name="viewport" content="width=device-width, user-scalable=no,maximum-scale=1.0,initial-scale=1">
         <title>商品详情</title>
         <link rel="stylesheet" type="text/css" href="http://static.liwushuo.com/static/web/apps/liwushuo/css/item_72d7c80.css">


         </head>
         <body>
         <p><img src="http://gw.alicdn.com/imgextra/i1/321318363/T2posDXBVXXXXXXXXX-321318363.jpg_620x10000q60.jpg" alt="" /><img src="http://gw.alicdn.com/imgextra/i1/321318363/T2krICXstaXXXXXXXX-321318363.jpg_620x10000q60.jpg" alt="" /><img src="http://gw.alicdn.com/imgextra/i1/321318363/T2f97HXqxXXXXXXXXX-321318363.jpg_620x10000q60.jpg" alt="" /><img src="http://gw.alicdn.com/imgextra/i4/321318363/T2YuEIXrRXXXXXXXXX-321318363.jpg_620x10000q60.jpg" alt="" /><img src="http://gw.alicdn.com/imgextra/i3/321318363/T2yJgCXxBaXXXXXXXX-321318363.jpg_620x10000q60.jpg" alt="" /><img src="http://gw.alicdn.com/imgextra/i4/321318363/T2hc3EXDVXXXXXXXXX-321318363.jpg_620x10000q60.jpg" alt="" /><img src="http://gw.alicdn.com/imgextra/i3/321318363/T2dloGXyRXXXXXXXXX-321318363.jpg_620x10000q60.jpg" alt="" /><img src="http://gw.alicdn.com/imgextra/i1/321318363/T2JDADXpBaXXXXXXXX-321318363.jpg_620x10000q60.jpg" alt="" /><img src="http://gw.alicdn.com/imgextra/i4/321318363/T2i0cEXD0XXXXXXXXX-321318363.jpg_620x10000q60.jpg" alt="" /><img src="http://gw.alicdn.com/imgextra/i3/321318363/T2zjADXEVXXXXXXXXX-321318363.jpg_620x10000q60.jpg" alt="" /><img src="http://gw.alicdn.com/imgextra/i3/321318363/T2bNZHXvVXXXXXXXXX-321318363.jpg_620x10000q60.jpg" alt="" /><img src="http://gw.alicdn.com/imgextra/i1/321318363/T2P9MDXpVaXXXXXXXX-321318363.jpg_620x10000q60.jpg" alt="" /><img src="http://gw.alicdn.com/imgextra/i4/321318363/T2qP.DXqxaXXXXXXXX-321318363.jpg_620x10000q60.jpg" alt="" /></p>
         </body>
         </html>
         * editor_id : 1024
         * favorited : false
         * favorites_count : 3258
         * id : 1001052
         * image_urls : ["http://img03.taobaocdn.com/bao/uploaded/i3/18363024574885733/T1IzloFh0gXXXXXXXX_!!0-item_pic.jpg_480x480q60.jpg","http://img04.taobaocdn.com/imgextra/i4/321318363/TB2pNIlaFXXXXa4XpXXXXXXXXXX_!!321318363.jpg_480x480q60.jpg"]
         * liked : false
         * likes_count : 3258
         * name : 台湾设计 地球仪灯
         * post_ids : ["120"]
         * price : 292.50
         * purchase_id : null
         * purchase_status : 3
         * purchase_type : 1
         * purchase_url : http://s.click.taobao.com/t?sche=liwushuo&e=m%3D2%26s%3DQrnWB8RwlWUcQipKwQzePOeEDrYVVa64Qih%2F7PxfOKS5VBFTL4hn2XyJgS%2FiZXC2lg6AtVBcXjxsOJRC4t67ybSnlAIVMwbDnEVXIc3MIL6QRwrNct1IPOwi13EI0KUc3%2BJgygY0jrsEv5zvzTw268YOae24fhW0
         * shares_count : 127
         * source : {"button_title":"去淘宝购买","name":"淘宝","page_title":"商品详情"}
         * subcategory_id : 21
         * updated_at : 1416364298
         * url : http://www.liwushuo.com/items/1001052
         */

        private Object brand_id;
        private Object brand_order;
        private int category_id;
        private int comments_count;
        private String cover_image_url;
        private int created_at;
        private String description;
        private String detail_html;
        private int editor_id;
        private boolean favorited;
        private int favorites_count;
        private int id;
        private boolean liked;
        private int likes_count;
        private String name;
        private String price;
        private Object purchase_id;
        private int purchase_status;
        private int purchase_type;
        private String purchase_url;
        private int shares_count;
        private SourceEntity source;
        private int subcategory_id;
        private int updated_at;
        private String url;
        private List<String> image_urls;
        private List<String> post_ids;

        public static DataEntity objectFromData(String str) {

            return new Gson().fromJson(str, DataEntity.class);
        }

        public void setBrand_id(Object brand_id) {
            this.brand_id = brand_id;
        }

        public void setBrand_order(Object brand_order) {
            this.brand_order = brand_order;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public void setComments_count(int comments_count) {
            this.comments_count = comments_count;
        }

        public void setCover_image_url(String cover_image_url) {
            this.cover_image_url = cover_image_url;
        }

        public void setCreated_at(int created_at) {
            this.created_at = created_at;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setDetail_html(String detail_html) {
            this.detail_html = detail_html;
        }

        public void setEditor_id(int editor_id) {
            this.editor_id = editor_id;
        }

        public void setFavorited(boolean favorited) {
            this.favorited = favorited;
        }

        public void setFavorites_count(int favorites_count) {
            this.favorites_count = favorites_count;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setLiked(boolean liked) {
            this.liked = liked;
        }

        public void setLikes_count(int likes_count) {
            this.likes_count = likes_count;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setPurchase_id(Object purchase_id) {
            this.purchase_id = purchase_id;
        }

        public void setPurchase_status(int purchase_status) {
            this.purchase_status = purchase_status;
        }

        public void setPurchase_type(int purchase_type) {
            this.purchase_type = purchase_type;
        }

        public void setPurchase_url(String purchase_url) {
            this.purchase_url = purchase_url;
        }

        public void setShares_count(int shares_count) {
            this.shares_count = shares_count;
        }

        public void setSource(SourceEntity source) {
            this.source = source;
        }

        public void setSubcategory_id(int subcategory_id) {
            this.subcategory_id = subcategory_id;
        }

        public void setUpdated_at(int updated_at) {
            this.updated_at = updated_at;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setImage_urls(List<String> image_urls) {
            this.image_urls = image_urls;
        }

        public void setPost_ids(List<String> post_ids) {
            this.post_ids = post_ids;
        }

        public Object getBrand_id() {
            return brand_id;
        }

        public Object getBrand_order() {
            return brand_order;
        }

        public int getCategory_id() {
            return category_id;
        }

        public int getComments_count() {
            return comments_count;
        }

        public String getCover_image_url() {
            return cover_image_url;
        }

        public int getCreated_at() {
            return created_at;
        }

        public String getDescription() {
            return description;
        }

        public String getDetail_html() {
            return detail_html;
        }

        public int getEditor_id() {
            return editor_id;
        }

        public boolean getFavorited() {
            return favorited;
        }

        public int getFavorites_count() {
            return favorites_count;
        }

        public int getId() {
            return id;
        }

        public boolean getLiked() {
            return liked;
        }

        public int getLikes_count() {
            return likes_count;
        }

        public String getName() {
            return name;
        }

        public String getPrice() {
            return price;
        }

        public Object getPurchase_id() {
            return purchase_id;
        }

        public int getPurchase_status() {
            return purchase_status;
        }

        public int getPurchase_type() {
            return purchase_type;
        }

        public String getPurchase_url() {
            return purchase_url;
        }

        public int getShares_count() {
            return shares_count;
        }

        public SourceEntity getSource() {
            return source;
        }

        public int getSubcategory_id() {
            return subcategory_id;
        }

        public int getUpdated_at() {
            return updated_at;
        }

        public String getUrl() {
            return url;
        }

        public List<String> getImage_urls() {
            return image_urls;
        }

        public List<String> getPost_ids() {
            return post_ids;
        }

        public static class SourceEntity {
            /**
             * button_title : 去淘宝购买
             * name : 淘宝
             * page_title : 商品详情
             */

            private String button_title;
            private String name;
            private String page_title;

            public static SourceEntity objectFromData(String str) {

                return new Gson().fromJson(str, SourceEntity.class);
            }

            public void setButton_title(String button_title) {
                this.button_title = button_title;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setPage_title(String page_title) {
                this.page_title = page_title;
            }

            public String getButton_title() {
                return button_title;
            }

            public String getName() {
                return name;
            }

            public String getPage_title() {
                return page_title;
            }
        }
    }
}
