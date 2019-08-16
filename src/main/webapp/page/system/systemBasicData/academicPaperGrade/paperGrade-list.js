layui.use(['form', 'layer', 'table', 'laytpl'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //信息列表
    var tableIns = table.render({
        elem: '#infoList',//数据表格id
        url: '/teacher_files_war/biz/paperGrade_findByPage.action',//数据接口
        page: true,//开启分页
        height: "full-125",//容器高度
        limits: [10, 15, 20, 25],
        limit: 20,
        id: "infoListTable",//给它一个id，用于下面更新表单内容
        cols: [[//表头
            {type: "checkbox", fixed: "left", width: 50},
            {field: 'id', title: '编号', align: 'center', width: 100},
            {field: 'title', title: '学术论文等级', minWidth: 100, align: 'center'},
            {title: '操作', minWidth: 175, templet: '#infoListBar', fixed: "right", align: "center"}
        ]]
    });

    $(".addBtn").click(function () {
        addUser();
    });

    //列表操作
    table.on('tool(infoList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;

        if (layEvent === 'edit') { //编辑
            addUser(data);
        } else if (layEvent === 'del') { //删除
            layer.confirm('确定删除此学术论文等级？', {icon: 3, title: '提示信息'}, function (index) {
                $.get("/teacher_files_war/biz/paperGrade_delete.action", {
                    id: data.id  //将需要删除的deptId作为参数传入
                }, function (data) {
                    if (data.code === 0) {
                        layer.msg("删除成功");
                    } else {
                        layer.msg("删除失败");
                    }
                    tableIns.reload();
                    layer.close(index);
                })
            });
        }
    });

    //添加用户
    function addUser(edit) {
        var index = layui.layer.open({
            title: "添加",
            type: 2,
            content: "paperGrade-add.html",
            maxmin: true,
            area: ['800px', '150px'],
            btn: ['确定', '取消'],
            success: function (layero, index) {
                var body = layui.layer.getChildFrame('body', index);
                if (edit) {
                    body.find(".Id").val(edit.id);
                    body.find(".title").val(edit.title);
                    body.find(".updateFlag").val(1);//更新
                    form.render();
                }
            },
            yes: function (index, layero) {
                var submit = layero.find('iframe').contents().find("#addUser");
                submit.click();
            }
        });
        window.sessionStorage.setItem("index", index);  //存放当前列表行数据
        $(window).on("resize", function () {
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }
});