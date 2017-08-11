package com.white.entity.system;

import com.white.entity.common.DataEntity;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by White on 2017/7/18.
 */
public class SysMenu extends DataEntity {
    // 父级编号
    private String parentId;
    // 所有父级编号
    private String parentIds;
    // 名称
    private String menuName;
    // 链接
    private String href;
    // 图标
    private String icon;
    // 排序
    private Integer sort;
    // 是否在菜单中显示（1：显示；0：不显示）
    private Boolean show;
    // 权限标识
    private String permission;
    // 备注
    private String remarks;
    // 是否是叶子节点
    private Boolean leaf = true;
    // 子节点
    private List<SysMenu> children = new ArrayList<>();

    public SysMenu() {
        super();
    }

    public SysMenu(String id) {
        super(id);
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public List<SysMenu> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenu> children) {
        this.children = children;
    }

    /**
     * 添加子节点
     * @param node 菜单节点
     */
    public void addChild(SysMenu node) {
        this.children.add(node);
    }

    @Override
    public String toString() {
        return "SysMenu{" +
                "parentId='" + parentId + '\'' +
                ", parentIds='" + parentIds + '\'' +
                ", menuName='" + menuName + '\'' +
                ", href='" + href + '\'' +
                ", icon='" + icon + '\'' +
                ", sort=" + sort +
                ", show=" + show +
                ", permission='" + permission + '\'' +
                ", remarks='" + remarks + '\'' +
                ", leaf=" + leaf +
                ", children=" + children +
                '}';
    }
}
