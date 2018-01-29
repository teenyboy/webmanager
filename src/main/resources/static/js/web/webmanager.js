var WEBMANAGER = window.NameSpace || {};

(function () {

    /**
     * 查询栏目网站信息
     */
    WEBMANAGER.queryWebs = function () {
        DEFAULT.ajaxQueryTable("/webmanager/queryWebs", null, "#websDataTables", null);
    };

    WEBMANAGER.addOrUpdateView = function (webmid) {
        var menuId = $("#menuId").val();
        window.location.href = "/webmanager/addOrUpdateView?webmid="+webmid+"&menuId="+menuId;
    };



    /**
     * 时间格式化
     * @param data
     * @returns {*}
     */
    WEBMANAGER.timeFormat = function (data) {
        return DEFAULT.formatTime(data,"YYYY-MM-DD HH:mm:ss");
    };

    /**
     * 操作按钮初始化
     * @param data
     * @returns {string}
     */
    WEBMANAGER.operation = function (data) {
        return '<div class="btn-group">\n' +
            '                      <button type="button" class="btn btn-info" onclick="WEBMANAGER.addOrUpdateView('+data+')"><i class="glyphicon glyphicon-pencil"></i></button>\n' +
            '                      <button type="button" style="margin-left: 10px;" class="btn btn-danger" onclick="WEBMANAGER.delWebContent('+data+')"><i class="glyphicon glyphicon-remove"></i></i></button>\n' +
            '                    </div>'
    };

    /**
     * 删除栏目
     * @param id
     */
    WEBMANAGER.delWebContent = function (id) {
        var data = {id: id};
        DEFAULT.Ajax("/webmanager/delWebContent", data, true, function (res) {
            alert(res.msg);
            window.location.reload();
        })
    };
})();