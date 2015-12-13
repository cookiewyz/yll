package com.xiaoliwu.yll.myinterface;

/**
 * 接口
 */
public interface ServerUrl {
    String HOT = "http://api.liwushuo.com/v2/items?limit=20&gender=1&generation=4";//热门地址
    String HOTDETAIL = "http://api.liwushuo.com/v2/items/";//热门商品详情地址
    String STRATEGY = "http://api.liwushuo.com/v2/collections?limit=10&offset=0";
    String STRATEGYCATEGORY = " http://api.liwushuo.com/v2/channel_groups/all";//品类  对象  场合  风格
    String CLASSIFYSTRATEGY = "http://api.liwushuo.com/v2/channel_groups/all";//分类中的攻略数据
    String HOME_GIFT = "http://api.liwushuo.com/v2/channels/111/items?limit=20";//礼物
    String HOME_FOOD = "http://api.liwushuo.com/v2/channels/118/items?limit=20";//美食
    String HOME_EL = "http://api.liwushuo.com/v2/channels/121/items?limit=20";//数码
    String HOME_RECREATION = "http://api.liwushuo.com/v2/channels/120/items?limit=20";//娱乐
    String HOME_EXERCISE = "http://api.liwushuo.com/v2/channels/123/items?limit=20";//运动
    String HOME_HAPPY = "http://api.liwushuo.com/v2/collections/22/posts?limit=20&offset=0";//美好小物
    String HOME_SPECIAL = "http://api.liwushuo.com/v1/collections?";//全部专题
    String HOME_ZHUJIEMIAN = "http://api.liwushuo.com/v2/channels/108/items?limit=20&ad=1&gender=1";//首页
    String HOME_DETAILS="http://api.liwushuo.com/v1/posts/";//攻略详情
    String HOME_COMMENT = "http://api.liwushuo.com/v1/posts/";//评论
    String HOME_HOT = "http://api.liwushuo.com/v2/search/hot_words";//标签
    String HOME_HOT_LIWU = "http://api.liwushuo.com/v2/search/item?limit=20&offset=0&sort=&keyword=";//搜索礼物界面
    String HOME_HOT_GONGLUE = "http://api.liwushuo.com/v2/search/post?limit=20&offset=0&sort=&keyword=";//搜索攻略界面
    String HOME_SELECT = "http://api.liwushuo.com/v2/search/item_filter";//挑选礼物界面
    String HOME_GOOD = "http://api.liwushuo.com/v1/posts/";//美好小物详情界面
    String HOME_ANREDU1 = "http://api.liwushuo.com/v2/search/post?limit=20&offset=0&sort=hot&keyword=";//按照热度排行
    String HOME_ANREDU = "http://api.liwushuo.com/v2/search/post?limit=20&offset=0&sort=hot&keyword=";//按照热度排行
    String HOME_JIAGEGAO = "http://api.liwushuo.com/v2/search/item?limit=20&offset=0&sort=price%3Aasc&keyword=";//价格由高到底
    String HOME_JIAGEDI = "http://api.liwushuo.com/v2/search/item?limit=20&offset=0&sort=price%3Adesc&keyword=";//价格由低到高
    String HOME_TIAOXUAN = "http://api.liwushuo.com/v2/search/item_by_type?limit=20&offset=";//礼物挑选界面
    String HOME_TIAOXUAN2 = "http://api.liwushuo.com/v2/search/item_by_type?target=16&limit=20&scene=&price=&personality=&offset=";
    String HOEM_NV = "http://api.liwushuo.com/v2/search/item_by_type?target=17&limit=20&scene=&price=&personality=&offset=";//女盆友
    String HOME_REDU = "http://api.liwushuo.com/v2/search/item_by_type?target=17&limit=20&scene=&price=&sort=price%3Aasc&personality=&offset=";//热度
    String HOME_GUIMI = "http://api.liwushuo.com/v2/search/item_by_type?target=18&limit=20&scene=&price=&sort=price%3Adesc&personality=&offset=";//闺蜜们
    String HOME_JIYOU = "http://api.liwushuo.com/v2/search/item_by_type?target=19&limit=20&scene=&price=&sort=price%3Adesc&personality=&offset=";//基友
    String HOME_BABAMAMA="http://api.liwushuo.com/v2/search/item_by_type?target=20&limit=20&scene=&price=&sort=price%3Adesc&personality=&offset=";//爸爸妈妈
}
