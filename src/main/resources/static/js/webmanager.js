var WEBMANAGER = window.NameSpace || {};

(function () {

    /**
     * 查询栏目网站信息
     */
    WEBMANAGER.queryWebs = function () {
        DEFAULT.ajaxQueryTable("/webmanager/queryWebs", null, "#websDataTables", null);
    };

    WEBMANAGER.addOrUpdateView = function (id) {
        var menuId = $("#menuId").val();
        var menuName = $("#menuName").val();
        window.location.href = "/webmanager/addOrUpdateView?id="+id+"&menuId="+menuId+"&menuName="+menuName;
    };

    /**
     * 时间格式化
     * @param data
     * @returns {*}
     */
    WEBMANAGER.timeFormat = function (data) {
        return DEFAULT.formatTime(data,"YYYY-MM-DD HH:mm:ss");
    };
})();