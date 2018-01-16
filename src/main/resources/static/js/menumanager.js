var MENUMANAGER = window.NameSpace || {};

(function () {

    MENUMANAGER.newMenuOpen = function () {
        $("#menuName").val("");
        $("#menuGrade").val("");
        $("#menusTitle").val("新增模块");
        $("#operationStyle").val("add");
        $("#newMenuModal").modal('show');
    };

    MENUMANAGER.newMenu = function () {

        var menuName = $("#menuName").val().trim();
        var menuGrade = $("#menuGrade").val().trim();

        if (DEFAULT.isEmptyStr(menuName)) {
            alert("输入非法，模块名称不能为空");
        }

        if (!DEFAULT.isEmptyStr(menuGrade)) {
            if (isNaN(parseInt(menuGrade))) {
                alert("输入非法，序号输入必须为数字");
            }
        }

        var opertionStyle = $("#operationStyle").val();

        var data = {name: menuName, grade: menuGrade,opertionStyle:opertionStyle};
        DEFAULT.Ajax("/menu/addOrUpdateMenu", data, true, function (res) {
            if (res.result == true) {
                alert(res.msg);
                $("#newMenuModal").modal('hide');
            } else {
                alert(res.msg);
            }
            MENUMANAGER.clear();
        })
    };

    /**
     * 退出及操作成功清理
     */
    MENUMANAGER.clear = function () {
        $("#menuName").val("");
        $("#menuGrade").val("");
        $("#menusTitle").val("");
        $("#operationStyle").val("");
        $("#newMenuModal").modal('hide');
    };

    MENUMANAGER.queryMenus = function () {
        DEFAULT.ajaxQueryNew("/menu/queryMenus", null, "#menusDataTables", null);
    };

    MENUMANAGER.operation = function (data) {
        return '<div class="btn-group">\n' +
            '                      <button type="button" class="btn btn-info" onclick="MENUMANAGER.updateMenusWindow('+data+')"><i class="glyphicon glyphicon-pencil"></i></button>\n' +
            '                      <button type="button" style="margin-left: 10px;" class="btn btn-danger" onclick="MENUMANAGER.delMenus('+data+')"><i class="glyphicon glyphicon-remove"></i></i></button>\n' +
            '                    </div>'
    };

    /**
     * 删除栏目
     * @param id
     */
    MENUMANAGER.delMenus = function (id) {
        var data = {id: id};
        DEFAULT.Ajax("/menu/delMenus", data, true, function (res) {
            alert(res.msg);
            window.location.reload();
        })
    };

    /**
     * 打开更新栏目窗口
     * @param id
     */
    MENUMANAGER.updateMenusWindow = function (id) {

        var data = {id:id};
        DEFAULT.Ajax("/menu/queryMenuById", data, true, function (res) {
            if (res.result == true) {
                $("#updateId").val(id);
                $("#menuName").val(res.name);
                $("#menuGrade").val(res.grade);
                $("#menusTitle").val("更新模块");
                $("#operationStyle").val("update");
                $("#newMenuModal").modal('show');
            } else {
                alert(res.msg);
            }
        })
    };

})();