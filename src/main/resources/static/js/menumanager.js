var MENUMANAGER = window.NameSpace || {};

(function () {

    MENUMANAGER.newMenuOpen = function () {
        $("#menuName").val("");
        $("#menuNum").val("");
        $("#newMenuModal").modal('show');
    };

    MENUMANAGER.newMenu = function () {

        var menuName = $("#menuName").val().trim();
        var menuNum = $("#menuNum").val().trim();

        if (DEFAULT.isEmptyStr(menuName)) {
            alert("输入非法，模块名称不能为空");
        }

        if (!DEFAULT.isEmptyStr(menuNum)) {
            if (isNaN(parseInt(menuNum))) {
                alert("输入非法，序号输入必须为数字");
            }
        }

        var data = {name: menuName, grade: menuNum};
        DEFAULT.Ajax("/menu/newMenu", data, true, function (res) {
            if (res.result == true) {
                alert(res.msg);
                $("#newMenuModal").modal('hide');
            } else {
                alert(res.msg);
            }
        })
    };


    MENUMANAGER.queryMenus = function () {
        DEFAULT.ajaxQueryNew("/menu/queryMenus", null, "#menusDataTables", null);
    };

    MENUMANAGER.operation = function (data) {
        return '<div class="btn-group">\n' +
            '                      <button type="button" class="btn btn-info"><i class="glyphicon glyphicon-pencil"></i></button>\n' +
            '                      <button type="button" style="margin-left: 10px;" class="btn btn-danger"><i class="glyphicon glyphicon-remove"></i></i></button>\n' +
            '                      <button type="button" style="margin-left: 10px;" class="btn btn-info"><i class="glyphicon glyphicon-info-sign"></i></button>\n' +
            '                    </div>'
    };
})();