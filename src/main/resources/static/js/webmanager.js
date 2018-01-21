var WEBMANAGER = window.NameSpace || {};

(function () {

    /**
     * 查询栏目网站信息
     */
    WEBMANAGER.queryWebs = function () {
        DEFAULT.ajaxQueryTable("/webmanager/queryWebs", null, "#websDataTables", null);
    };
})();