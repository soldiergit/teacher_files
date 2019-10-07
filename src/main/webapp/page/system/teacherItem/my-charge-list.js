layui.config({
    base: '/teacher_files_war/static/layui_exts/'
}).extend({
    excel: 'excel'
}).use(['form','layer','table','excel','laytpl','util'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        excel = layui.excel,
        laytpl = layui.laytpl,
        util = layui.util,
        table = layui.table;

    var teacherId = window.sessionStorage.getItem("teacherId");
    //信息列表
    var tableIns = table.render({
        elem: '#infoList',//数据表格id
        url: '/teacher_files_war/biz/teacherItem_findByPerson.action?itemPersonId='+teacherId,//数据接口
        page : true,//开启分页
        height : "full-125",//容器高度
        limits : [10,15,20,25],
        limit : 20,
        toolbar: '#toolbar',//头部工具栏
        title: '我主持的项目',//用于导出
        id : "infoListTable",//给它一个id，用于下面更新表单内容
        cols : [[//表头
            {type: "checkbox", fixed:"left", width:50},
            // {field: 'itemId', title: 'Id', align:'center'},
            {field: 'itemName', title: '项目名称', minWidth:250, align:"center"},
            {field: 'personName', title: '主持人', minWidth:100, align:'center',templet:function(d){
                    return d.itemPerson.teacherName;
                }},
            {field: 'itemType', title: '项目类型', minWidth:100, align:'center',templet:function(d){
                    if (d.itemType=='1') return '教改类';
                    if (d.itemType=='2') return '科研类';
                }},
            {field: 'itemCategory', title: '项目类别', minWidth:200, align:'center',templet:function(d){
                    return d.itemCategory.title;
                }},
            {field: 'createTime', title: '立项时间', minWidth:100, align:'center',templet: function (d) {
                    return util.toDateString(d.createTime, "yyyy年")
                }},
            {field: 'itemMoney', title: '项目经费', minWidth:100, align:'center', templet: function (d) {
                    return d.itemMoney+"/万元"
                }},
            {field: 'itemLevel', title: '项目级别', minWidth:100, align:'center',templet:function(d){
                    return d.itemLevel.title;
                }},
            // {field: 'contractNumber', title: '合同编号', minWidth:100, align:'center'},
            {field: 'memberName', title: '成员', minWidth:100, align:'center'},
            // {field: 'startTime', title: '起始时间', minWidth:100, align:'center',templet: function (d) {
            //         return util.toDateString(d.startTime, "yyyy年MM月dd日")
            //     }},
            // {field: 'endTime', title: '结束时间', minWidth:100, align:'center',templet: function (d) {
            //         return util.toDateString(d.endTime, "yyyy年MM月dd日")
            //     }},
            {title: '操作', minWidth: 150, templet: '#infoListBar', fixed: "right", align: "center"}
        ]]
    });

    var $ = layui.$, active = {
        reload: function(){
            var dataReload = $('#dataReload');
            //执行重载
            table.reload('infoListTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    key: dataReload.val()
                }
            }, 'data');
        }
    };

    $('.layui-card-body .reloadBtn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    $(".addBtn").click(function(){
        addUser();
    });

    //列表操作
    table.on('tool(infoList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            addUser(data);
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此记录？',{icon:3, title:'提示信息'},function(index){
                $.get("/teacher_files_war/biz/teacherItem_delete.action",{
                    itemId : data.itemId  //将需要删除的newsId作为参数传入
                },function(data){
                    if (data.code === 0){
                        layer.msg("删除成功");
                    }else {
                        layer.msg("删除失败");
                    }
                    tableIns.reload();
                    layer.close(index);
                })
            });
        }else if(layEvent === 'detail'){ //详情
            detailUser(data)
        }
    });

    //批量删除
    $(".delBtn").click(function(){
        var checkStatus = table.checkStatus('infoListTable'),
            data = checkStatus.data,
            newsId = [];
        if(data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].itemId);
            }
            layer.confirm('确定删除选中记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.post("/teacher_files_war/biz/teacherItem_deleteBatch.action",{
                    ids : newsId.join(',') //将需要删除的newsId作为参数传入
                },function(data){
                    if (data.code===0){
                        layer.msg("删除成功");
                    }else {
                        layer.msg("删除失败");
                    }
                    tableIns.reload();
                    layer.close(index);
                })
            })
        }else{
            layer.msg("请选择需要删除的科研项目");
        }
    });

    //exportCode:   1-系统管理员导出整个表 2-部门领导和部门负责人导出本学院全部 3-主持人导出全部 4-根据id导出
    //批量导出
    $(".exportBtn").click(function(){
        var checkStatus = table.checkStatus('infoListTable'),
            data = checkStatus.data,
            newsId = [];
        if(data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].itemId);
            }
            $.ajax({
                url: '/teacher_files_war/biz/teacherItem_findByExport.action',
                data: {ids: newsId.join(','), exportCode: 4},
                dataType: 'json',
                success: function (res) {
                    if (res.code===0){
                        var data = res.data;
                        // 重点！！！如果后端给的数据顺序和映射关系不对，请执行梳理函数后导出
                        data = excel.filterExportData(data, {
                            itemId: 'itemId',
                            itemName: function(value, line, data) {
                                return {
                                    v: value,
                                    s: { font: { sz: 14, bold: true, color: { rgb: "FFFFAA00" } }, fill: { bgColor: { indexed: 64 }, fgColor: { rgb: "FFFF00" } } }
                                };
                            },
                            personName: function (value, line, data) {
                                return value.teacherName;
                            },
                            itemType: function (value, line, data) {
                                if (value=='1') return '教改类';
                                if (value=='2') return '科研类';
                            },
                            itemCategory: function (value, line, data) {
                                return value.title;
                            },
                            createTime: function (value, line, data) {
                                return util.toDateString(value, "yyyy年");
                            },
                            itemMoney: function (value, line, data) {
                                return value+"/万元";
                            },
                            itemLevel: function (value, line, data) {
                                return value.title;
                            },
                            memberName: 'contractNumber',
                            contractNumber: 'contractNumber',
                            startTime: function (value, line, data) {
                                return util.toDateString(value, "yyyy年MM月dd日");
                            },
                            endTime: function (value, line, data) {
                                return util.toDateString(value, "yyyy年MM月dd日");
                            },

                        });
                        // 重点2！！！一般都需要加一个表头，表头的键名顺序需要与最终导出的数据一致
                        data.unshift({itemId: '编号', itemName: '项目名称', personName: '主持人', awardee: '获奖人', itemType: '项目类型',
                            itemCategory: '项目类别', createTime: '立项时间', itemMoney: '项目经费', itemLevel: '项目级别', memberName: '成员',
                            contractNumber: '合同编号', startTime: '起始时间', endTime: '结束时间'})

                        excel.exportExcel({
                            sheet1: data
                        }, '我主持的部分教师项目.xlsx', 'xlsx');
                    }else {
                        layer.msg("导出失败");
                    }
                }
            })
        }else{
            layer.msg("请选择需要导出的记录");
        }
    });
    //全部导出
    $(".exportAllBtn").click(function(){
        $.ajax({
            url: '/teacher_files_war/biz/teacherItem_findByExport.action',
            data: {teacherId: teacherId, exportCode: 3},
            dataType: 'json',
            success: function (res) {
                if (res.code===0){
                    var data = res.data;
                    // 重点！！！如果后端给的数据顺序和映射关系不对，请执行梳理函数后导出
                    data = excel.filterExportData(data, {
                        itemId: 'itemId',
                        itemName: function(value, line, data) {
                            return {
                                v: value,
                                s: { font: { sz: 14, bold: true, color: { rgb: "FFFFAA00" } }, fill: { bgColor: { indexed: 64 }, fgColor: { rgb: "FFFF00" } } }
                            };
                        },
                        itemPerson: function (value, line, data) {
                            return value.teacherName;
                        },
                        itemType: function (value, line, data) {
                            if (value=='1') return '教改类';
                            if (value=='2') return '科研类';
                        },
                        itemCategory: function (value, line, data) {
                            return value.title;
                        },
                        createTime: function (value, line, data) {
                            return util.toDateString(value, "yyyy年");
                        },
                        itemMoney: function (value, line, data) {
                            return value+"/万元";
                        },
                        itemLevel: function (value, line, data) {
                            return value.title;
                        },
                        memberName: 'contractNumber',
                        contractNumber: 'contractNumber',
                        startTime: function (value, line, data) {
                            return util.toDateString(value, "yyyy年MM月dd日");
                        },
                        endTime: function (value, line, data) {
                            return util.toDateString(value, "yyyy年MM月dd日");
                        },

                    });
                    // 重点2！！！一般都需要加一个表头，表头的键名顺序需要与最终导出的数据一致
                    data.unshift({itemId: '编号', itemName: '项目名称', itemPerson: '主持人', awardee: '获奖人', itemType: '项目类型',
                        itemCategory: '项目类别', createTime: '立项时间', itemMoney: '项目经费', itemLevel: '项目级别', memberName: '成员',
                        contractNumber: '合同编号', startTime: '起始时间', endTime: '结束时间'})

                    excel.exportExcel({
                        sheet1: data
                    }, '我主持的教师项目.xlsx', 'xlsx');
                }else {
                    layer.msg("导出失败");
                }
            }
        })
    });

    //添加用户
    function addUser(edit){
        var index = layui.layer.open({
            title : "添加",
            type : 2,
            content : "item-add.html",
            maxmin: true,
            area: ['800px', '800px'],
            btn: ['确定', '取消'],
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find(".Id").val(edit.itemId);
                    body.find(".itemName").val(edit.itemName);
                    body.find(".itemType").val(edit.itemType);
                    body.find(".contractNumber").val(edit.contractNumber);
                    body.find(".itemMoney").val(edit.itemMoney);
                    body.find(".createTime").val(util.toDateString(edit.createTime, "yyyy"));
                    body.find(".startTime").val(util.toDateString(edit.startTime, "yyyy-MM-dd"));
                    body.find(".endTime").val(util.toDateString(edit.endTime, "yyyy-MM-dd"));
                    body.find(".itemLevel").val(edit.itemLevel);
                    body.find(".personHide").val(edit.itemPerson.teacherId);//使用中间变量记录负责人
                    body.find(".memberHide").val(edit.itemMember);//使用中间变量记录成员
                    body.find(".itemCategoryHide").val(edit.itemCategory.id);//使用中间变量记录项目信息
                    body.find(".itemLevelHide").val(edit.itemLevel.id);
                    body.find(".updateFlag").val(1);//更新

                    //回显示文件
                    $.ajax({
                        type: "POST",
                        url: '/teacher_files_war/biz/teacherItemAnnex_findByTeacherItemId.action',//数据接口
                        data: {itemId: edit.itemId},
                        success: function (data) {
                            if (data.code === 0) {
                                $.each(data.data, function (index, item) {
                                    var oldtr = $(['<tr id="upload-'+ index +'">',
                                        '<td>'+ item.fileName +'</td>',
                                        // '<td>'+ (file.size/1014).toFixed(1) +'kb</td>',
                                        '<td></td>',
                                        '<td>已上传</td>',
                                        '<td>',
                                        '<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>',
                                        '</td>',
                                        '</tr>'].join(''));

                                    //删除
                                    oldtr.find('.demo-delete').on('click', function(){
                                        $.post("/teacher_files_war/biz/teacherItemAnnex_delete.action", {
                                            itemAnnexId: item.itemAnnexId //将需要删除的itemAnnexId作为参数传入
                                        }, function (data) {
                                            if (data.code === 0) {
                                                layer.msg("删除成功");
                                            } else {
                                                layer.msg("删除失败");
                                            }
                                            tableIns.reload();
                                            layer.close(index);
                                        });
                                        oldtr.remove();//删除当前
                                    });

                                    body.find("#demoList").append(oldtr);
                                });
                            } else {
                                layer.msg("未知错误，请联系管理员！" + data.msg);
                            }

                        },
                        error: function () {
                            layer.msg("可能是因为网络原因操作失败了，请重试，若多次重试不成功，请于网站管理员联系");
                        }
                    });
                    form.render();
                }
            },
            yes: function (index, layero) {
                //点击确认触发 iframe 内容中的按钮提交
                var submit = layero.find('iframe').contents().find("#addUser");
                submit.click();
            }
        });
        // layui.layer.full(index);  //全屏
        window.sessionStorage.setItem("index",index);  //存放当前列表行数据
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }

    //详情
    function detailUser(data){
        var index = layui.layer.open({
            title : "详情",
            type : 2,
            content : "item-detail.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                body.find(".itemId").val(data.itemId);
                body.find(".itemName").val(data.itemName);
                body.find(".itemType").val(data.itemType);
                body.find(".itemCategory").val(data.itemCategory.title);
                body.find(".itemLevel").val(data.itemLevel.title);
                body.find(".contractNumber").val(data.contractNumber);
                body.find(".itemMoney").val(data.itemMoney);
                body.find(".createTime").val(util.toDateString(data.createTime, "yyyy"));
                body.find(".startTime").val(util.toDateString(data.startTime, "yyyy-MM-dd"));
                body.find(".endTime").val(util.toDateString(data.endTime, "yyyy-MM-dd"));
                body.find(".personName").val(data.itemPerson.teacherName);//负责人
                body.find(".memberName").val(data.memberName);//成员

                var str = "";   //附件详情
                $.ajax({
                    type: "POST",
                    url: '/teacher_files_war/biz/teacherItemAnnex_findByTeacherItemId.action',//数据接口
                    data: {itemId: data.itemId},
                    success: function (data) {
                        if (data.code === 0) {
                            $.each(data.data, function (index, item) {
                                str += '<a class="fileCss" href="/teacher_files_war/download/download_downloadFile.action?' +
                                    'downloadPath=' + item.filePath + '&filename=' + item.fileName + '">' + item.fileName + '</a><br>';
                                // 'downloadPath=' + item.path + '&filename=《' + data.paperName + '》附件' + index + '">《' + data.paperName + '》附件' + index + '</a><br>';
                            });
                            body.find("#manyFile").html(str);
                        }
                    }});

                form.render();
            }
        });
        layui.layer.full(index);  //全屏
        window.sessionStorage.setItem("index",index);  //存放当前列表行数据
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }
});