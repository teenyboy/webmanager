var WEBMANAGER = window.NameSpace || {};

(function () {

    /**
     * 查询栏目网站信息
     */
    WEBMANAGER.queryWebs = function (data) {
        DEFAULT.ajaxQueryTable("/webmanager/queryWebs", data, "#websDataTables", null);
    };

    WEBMANAGER.addOrUpdateView = function (webmid) {
        var menuId = $("#menuId").val();
        window.location.href = "/webmanager/addOrUpdateView?webmid="+webmid+"&menuId="+menuId;
    };

    /**
     * 置顶
     * @param webmid
     */
    WEBMANAGER.makeUp = function (webmid) {
        var data = {id:webmid};
        DEFAULT.Ajax("/webmanager/makeUp",data,false,function (res) {
            window.location.reload();
        })
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
            '                      <button type="button" class="btn btn-info" onclick="WEBMANAGER.makeUp('+data+')"><i class="glyphicon glyphicon-upload"></i></button>\n' +
            '                      <button type="button" style="margin-left: 10px;" class="btn btn-info" onclick="WEBMANAGER.addOrUpdateView('+data+')"><i class="glyphicon glyphicon-pencil"></i></button>\n' +
            '                      <button type="button" style="margin-left: 10px;" class="btn btn-danger" onclick="WEBMANAGER.delWebContent('+data+')"><i class="glyphicon glyphicon-remove"></i></i></button>\n' +
            '                    </div>'
    };


    /**
     * 处理文本太长
     * @param data
     * @returns {string}
     */
    WEBMANAGER.tolongContent = function (data) {
        return "<div style='width:200px;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;' title="+data+">"+data+"</div>"
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