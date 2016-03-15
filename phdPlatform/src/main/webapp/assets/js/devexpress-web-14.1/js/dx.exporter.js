/*! 
 * DevExtreme Exporter
 * Version: 14.1.7
 * Build date: Sep 22, 2014
 *
 * Copyright (c) 2012 - 2014 Developer Express Inc. ALL RIGHTS RESERVED
 * EULA: https://www.devexpress.com/Support/EULAs/DevExtreme.xml
 */
"use strict";
DevExpress.MOD_TMP_WIDGETS_FOR_EXPORTER || (function (n, t, i) {
    var u = t.ui, f = t.utils, e = u.events, o = "dx-menu", r = o + "-item", a = "dx-state-hover", v = r + "-text", s = r + "-popout", y = s + "-container", p = r + "-disabled", h = r + "-selected", c = r + "-wrapper", w = o + "-rtl", l = "dx-icon", b = u.CollectionContainerWidget.inherit({_itemRenderDefault: function (t, r, u) {
            var e, o;
            f.isDefined(t) && (t.visible === i || t.visible || u.hide(), t.disabled && u.addClass(p), o = !!this.option("rtlEnabled"), o && u.addClass(w), e = n("<div>").addClass("dx-menu-item-content").appendTo(u), this._renderImage(t, e), this._renderCaption(t, e), this._renderPopout(t, e))
        }, _renderImage: function (t, i) {
            var r, u;
            t.icon ? r = n("<span>").addClass(l + "-" + t.icon).appendTo(i) : t.iconSrc && (r = n("<img>").attr("src", t.iconSrc).appendTo(i)), r && r.addClass(l)
        }, _renderPopout: function (t, i) {
            var u, r;
            this._hasChildren(t) && (r = n("<span>").addClass(y).appendTo(i), u = n("<div>").addClass(s).appendTo(r))
        }, _renderCaption: function (t, i) {
            var r;
            r = n("<span>").addClass(v), n.isPlainObject(t) ? t.text && r.text(t.text) : r.html(String(t)), i.append(r)
        }, _renderItemHotTrack: function (n, t) {
            var i, r;
            (r = n.data(this._itemDataKey()), r == this.option("selectedItem") && t) || !this.option("hoverStateEnabled") || (i = n.parents("." + c).eq(0), i.length && i.toggleClass(a, t))
        }, _itemDataKey: function () {
            return"dxMenuItemDataKey"
        }, _itemClass: function () {
            return r
        }, _itemWrapperSelector: function () {
            return"." + c
        }, _attachItemHoverEvents: function () {
            var t = this._itemWrapperSelector(), i, r, u = this._createAction(n.proxy(function (n) {
                this._handleItemMouseEnter(n.jQueryEvent)
            }, this)), f = this._createAction(n.proxy(function (n) {
                this._handleItemMouseLeave(n.jQueryEvent)
            }, this));
            i = e.addNamespace("mouseenter", this.NAME), r = e.addNamespace("mouseleave", this.NAME);
            this._itemContainer().off(i, t).on(i, t, n.proxy(function (n) {
                u({jQueryEvent: n})
            }, this));
            this._itemContainer().off(r, t).on(r, t, n.proxy(function (n) {
                f({jQueryEvent: n})
            }, this))
        }, _handleItemMouseMove: function (n) {
            var t = this, i = t._getItemElementByEventArgs(n);
            t._itemX || (t._itemX = 0), t._itemY || (t._itemY = 0), (Math.abs(event.pageX - t._itemX) > 4 || Math.abs(event.pageY - t._itemY) > 4) && (t._itemX = event.pageX, t._itemY = event.pageY, t._handleItemMouseMoveCore(i))
        }, _hasChildren: function (n) {
            return n.items && n.items.length > 0
        }, _addCustomCssClass: function (t) {
            var i = n.trim(this.option("cssClass"));
            i && t.addClass(i)
        }, _init: function () {
            var n = this.option("items");
            (this.callBase(), this.selectedItem = this.option("selectedItem"), this.enableSelection = this.option("allowSelection"), f.isDefined(this.selectedItem) && this.selectedItem != null) || (this.selectedItem = this._getLastSelectedItem(n), this.selectedItem && this._setOptionInternal("selectedItem", this.selectedItem))
        }, _setOptionInternal: function (n, t) {
            this._optionChangedInternalFlag = !0, this.option(n, t), this._optionChangedInternalFlag = !1
        }, _getLastSelectedItem: function (t) {
            var u = this, i = null, r;
            return n.each(t, function (n, t) {
                t && (t.selected && t.selectable && (i = t), f.isArray(t.items) && (r = u._getLastSelectedItem(t.items), r && (i = r)))
            }), i
        }, _updateSelectedItemOnClick: function (n, t) {
            var r = this.$selectedItemWrapper ? this.$selectedItemWrapper.children(".dx-menu-item").eq(0).data(this._itemDataKey()) : null, i = {selectedItem: t, previousSelectedItem: r, itemElement: n, itemData: t}, u = this._createActionByOption("itemSelectAction", i);
            this.option("allowSelectOnClick") && t.selectable && (this.$selectedItemWrapper && (this.$selectedItemWrapper.removeClass(h), this.$selectedItemWrapper.children(".dx-menu-item").eq(0).data(this._itemDataKey()) && (this.$selectedItemWrapper.children(".dx-menu-item").eq(0).data(this._itemDataKey()).selected = !1)), this._setOptionInternal("selectedItem", null), this.$selectedItemWrapper = n.parents(".dx-menu-item-wrapper").eq(0), this.$selectedItemWrapper.addClass(h), this._setOptionInternal("selectedItem", t), t.selected = !0, u(i))
        }, _handleItemClick: function (t) {
            var i = this._createAction(n.proxy(this._updateOverlayVisibilityOnClick, this));
            this._handleItemJQueryEvent(t, "itemClickAction", {}, {afterExecute: n.proxy(i, this)})
        }, _getItemElementByEventArgs: function (t) {
            return n(t.currentTarget).children(this._itemSelector()).first()
        }});
    u.dxMenuBase = b
}(jQuery, DevExpress), function (n, t, i) {
    var o = t.ui, l = t.utils, r = o.events, s = t.fx, g = "dxContextMenu", f = "dx-menu", e = f + "-item", nt = e + "-popout", a = e + "-selected", u = "dx-state-hover", v = "dx-menu-phone-overlay", y = f + "-items-container", h = e + "-wrapper", p = "dx-submenu", w = "dxSubMenuLevel", c = "dx-context-menu", b = "dx-widget", k = f + "-separator", d = c + "-content-delimiter";
    t.registerComponent("dxContextMenu", o.dxMenuBase.inherit({_deprecatedOptions: {direction: {since: "14.1", message: "Use the 'submenuDirection' option instead."}, allowSelectItem: {since: "14.1", message: "Use the 'allowSelection' option instead."}}, _optionAliases: {direction: "submenuDirection", allowSelectItem: "allowSelection"}, _setDefaultOptions: function () {
            this.callBase(), this.option({items: [], showSubmenuMode: "onHover", cssClass: "", invokeOnlyFromCode: !1, position: {at: "top left", my: "top left"}, hoverStateEnabled: !0, allowSelection: !0, allowSelectItem: !0, allowSelectOnClick: !0, selectedItem: null, itemSelectAction: null, animation: {show: {type: "fade", from: 0, to: 1, duration: 100}, hide: {type: "fade", from: 1, to: 0, duration: 100}}, showingAction: null, shownAction: null, hidingAction: null, hiddenAction: null, positioningAction: null, submenuDirection: "auto", direction: "auto", visible: !1, target: window})
        }, _optionsByReference: function () {
            return n.extend(this.callBase(), {animation: !0, position: !0, selectedItem: !0})
        }, _itemContainer: function () {
            return this._overlay.content()
        }, _clean: function () {
            this._overlay && (this._overlay._element().remove(), this._overlay = null), this._target.off(r.addNamespace("dxhold", this.NAME)), this._target.off(r.addNamespace("contextmenu", this.NAME))
        }, _optionChanged: function (n, t) {
            if (!this._optionChangedInternalFlag)
                switch (n) {
                    case"visible":
                        this._toggleVisibility(t);
                        break;
                    case"disable":
                        break;
                    case"invokeOnlyFromCode":
                        break;
                    case"items":
                        this._overlay.option("visible") && this._overlay.toggle(!1), this.selectedItem = this._getLastSelectedItem(t), this._setOptionInternal("selectedItem", this.selectedItem), this.callBase.apply(this, arguments);
                        break;
                    case"selectedItem":
                        this.selectedItem = t;
                        break;
                    default:
                        this._overlay.option("visible") && this._overlay.toggle(!1), this.callBase.apply(this, arguments)
                    }
        }, _toggleVisibility: function (n) {
            n ? this.show() : this.hide()
        }, _render: function () {
            this._target = n(this.option("target")), this.option("position"), this.callBase()
        }, _renderContentImpl: function () {
            this._renderContextMenuOverlay(), this._renderAdditionGraphicsElements(), this._attachItemHoverEvents(), this._attachShowContextMenuEvents(), this._addCustomCssClass(this._element()), this.callBase()
        }, _renderDimensions: function () {
        }, _renderContextMenuOverlay: function () {
            var i, t, r = this._getOverlayOptions();
            i = n("<div>").appendTo(this._$element), i.dxOverlay(r), this._overlay = i.dxOverlay("instance"), t = this._overlay.content(), t.addClass(c).addClass(b), this._addCustomCssClass(t), this._addPlatformDependentClass(t), this.option("visible") && this.show()
        }, _addPlatformDependentClass: function (n) {
            t.devices.current().phone && n.addClass(v)
        }, _renderAdditionGraphicsElements: function () {
            this.option("showAdditionalElements") && (this.$contentDelimiter = n("<div>").appendTo(this._itemContainer()), this.$contentDelimiter.addClass(d), this.$contentDelimiter.css("position", "absolute").css("display", "none").css("z-index", "2000"))
        }, _attachShowContextMenuEvents: function () {
            var t = this, i = r.addNamespace("dxhold", this.NAME), u = r.addNamespace("contextmenu", this.NAME), f = this._createAction(n.proxy(function (n) {
                t.option("invokeOnlyFromCode") || n.jQueryEvent.originalEvent && n.jQueryEvent.originalEvent.pointerType !== "mouse" && t.show(n.jQueryEvent)
            }, this)), e = this._createAction(n.proxy(function (n) {
                t.option("invokeOnlyFromCode") || t.show(n.jQueryEvent) && (n.jQueryEvent.preventDefault(), n.jQueryEvent.originalEvent && (n.jQueryEvent.originalEvent.returnValue = !1))
            }, this));
            this._target.off(i).on(i, n.proxy(function (n) {
                f({jQueryEvent: n})
            }, this));
            this._target.off(u).on(u, n.proxy(function (n) {
                e({jQueryEvent: n})
            }, this))
        }, _renderItems: function (n) {
            var t = 1;
            this._renderSubMenu(n, this._itemContainer(), t)
        }, _renderSubMenu: function (t, i, r) {
            var u = this, e, f, o, s;
            e = n("<div>").appendTo(i), e.addClass(p), e.data(w, r), e.css("display", r === 1 ? "block" : "none"), f = n("<ul>").appendTo(e), f.addClass(y), r === 1 && (u.option("width") && f.css("min-width", u.option("width")), u.option("height") && f.css("min-height", u.option("height"))), n.each(t, function (t, i) {
                i && (u._renderSeparator(i, t, f), o = n("<li>").appendTo(f), o.addClass(h), s = u._renderItem(t, i, o), u.enableSelection && i === u.selectedItem && (o.addClass(a), u.$selectedItemWrapper = o), u._hasChildren(i) && u._renderSubMenu(i.items, s, ++r))
            })
        }, _renderSeparator: function (t, i, r) {
            var u;
            t.beginGroup && i > 0 && (u = n("<li>").appendTo(r), u.addClass(k))
        }, _showAdditionalGraphicsElements: function () {
            var r = this._itemContainer().children(".dx-submenu").eq(0), i = this.option("position").of, u = this.option("position").at, f = this.option("position").my, n = {of: r};
            this.$contentDelimiter && (this.$contentDelimiter.css("display", "block"), u === "left bottom" && f === "left top" || u === "right bottom" && f === "right top" ? (this.$contentDelimiter.width(i.width() < r.width() ? i.width() - 2 : r.width() - 2), this._itemContainer().offset().top > i.offset().top ? (this.$contentDelimiter.height(2), Math.round(this._itemContainer().offset().left) < Math.round(i.offset().left) ? (n.offset = "-1 -1", n.at = "right top", n.my = "right top") : (n.offset = "1 -1", n.at = "left top", n.my = "left top")) : (this.$contentDelimiter.height(3), Math.round(this._itemContainer().offset().left) < Math.round(i.offset().left) ? (n.offset = "-1 2", n.at = "right bottom", n.my = "right bottom") : (n.offset = "1 2", n.at = "left bottom", n.my = "left bottom"))) : (this.$contentDelimiter.width(2), this.$contentDelimiter.height(i.height() < r.height() ? i.height() - 2 : r.height() - 2), this._itemContainer().offset().top < i.offset().top ? (n.offset = u === "right top" ? "-1 -1" : "1 -1", n.at = u === "right top" ? "left bottom" : "right bottom", n.my = n.at) : (n.offset = u === "right top" ? "-1 1" : "1 1", n.at = f, n.my = f)), t.position(this.$contentDelimiter, n))
        }, _getOverlayOptions: function () {
            var t = this, i = this.option("animation");
            return{animation: i, closeOnOutsideClick: n.proxy(t._closeOnOutsideClickHandler, t), closeOnTargetScroll: !0, deferRendering: !1, disabled: this.option("disabled"), position: {at: this.option("position").at, my: this.option("position").my, of: this._target}, shading: !1, showTitle: !1, height: "auto", width: "auto", rtlEnabled: this.option("rtlEnabled"), showingAction: n.proxy(t._overlayShowingActionHandler, t), shownAction: n.proxy(t._overlayShownActionHandler, t), hidingAction: n.proxy(t._overlayHidingActionHandler, t), hiddenAction: n.proxy(t._overlayHiddenActionHandler, t), positionedAction: n.proxy(t._overlayPositionedActionHandler, t)}
        }, _removeHoverClass: function () {
            var t = this, i = t._overlay._$container.find(".dx-state-hover");
            n.each(i, function (t, i) {
                n(i).removeClass(u)
            })
        }, _searchActiveItem: function (t) {
            return n(t.parentElement.parentElement).hasClass("dx-menu-item-content") ? t.parentElement.parentElement.parentElement : n(t.parentElement).hasClass("dx-menu-item-content") ? t.parentElement.parentElement : n(t).hasClass("dx-menu-item-content") ? t.parentElement : i
        }, _closeOnOutsideClickHandler: function (t) {
            var f = this, h = f._overlay._$container.find(".dx-state-hover"), r = i, o = !1, c = n.extend([], f._shownSubMenus), s, e;
            return t.target === document ? (this._removeHoverClass(), !0) : (r = this._searchActiveItem(t.target), r !== i ? (n.each(h, function (t, i) {
                r.parentElement.parentElement === i.parentElement && (o = !0), o && r.parentElement !== i && n(i).removeClass(u)
            }), this.option("showSubmenuMode") === "onclick" && (s = n(r).find(".dx-submenu"), s.length > 0 && n.each(c, function (t, i) {
                e = f._searchActiveItem(i.context).parentElement, e.parentElement === n(r)[0].parentElement.parentElement && e !== n(r)[0].parentElement && f._hideChildrenSubMenus(i)
            })), !1) : (this._removeHoverClass(), !0))
        }, _overlayShowingActionHandler: function (n) {
            var t = this._createActionByOption("showingAction", {});
            t(n)
        }, _overlayShownActionHandler: function (n) {
            var t = this._createActionByOption("shownAction", {});
            t(n)
        }, _overlayHidingActionHandler: function (n) {
            var t = this._createActionByOption("hidingAction", {});
            t(n), this._hideAllShownSubMenus()
        }, _overlayHiddenActionHandler: function (n) {
            var t = this._createActionByOption("hiddenAction", {});
            t(n), this._setOptionInternal("visible", !1)
        }, _overlayPositionedActionHandler: function () {
            this._showAdditionalGraphicsElements()
        }, _handleItemMouseEnter: function (t) {
            var i = this, c = r.addNamespace("mousemove", i.NAME), f = i._getItemElementByEventArgs(t), l = f.children(".dx-submenu"), a = i.option("showSubmenuMode"), y = n.extend([], i._shownSubMenus), o, e, p = f.parents("." + h).eq(0), v, s;
            if ((n.each(y, function (r, f) {
                o = i._getItemElementByEventArgs(t), e = n(f.context), s = !1, f.context.parentElement === o[0].parentElement.parentElement && f.context !== o[0].parentElement && (v = i._overlay._$container.find(".dx-state-hover"), n.each(v, function (t, i) {
                    i.parentElement === e.context.parentElement && (s = !0), s && i.parentElement !== e.context.parentElement && n(i).removeClass(u)
                }), i._hideChildrenSubMenus(f), e.removeClass(u))
            }), !f.data(this._itemDataKey()).disabled) && (i._renderItemHotTrack(f, !0), this.option("showSubmenuMode") !== "onclick" && l.length > 0 && (i._showSubMenuGroup(l, f), p.addClass(u), a && a.toLowerCase() === "onhoverstay")))
                f.off(c).on(c, function (n) {
                    i._handleItemMouseMove(n)
                })
        }, _handleItemMouseLeave: function (n) {
            var i = this, t = i._getItemElementByEventArgs(n), r = t.children(".dx-submenu");
            t.data(this._itemDataKey()).disabled || r.is(":visible") || i._renderItemHotTrack(t, !1)
        }, _handleItemMouseMoveCore: function (n) {
            var t = n.children(".dx-submenu");
            this._showSubMenuGroup(t, n)
        }, _hideSubMenuGroup: function (n) {
            var t = this;
            this._isSubMenuVisible(n) && t._hideSubMenuCore(n)
        }, _showSubMenuGroup: function (n, t, i) {
            var r = this;
            (i || !this._isSubMenuVisible(n)) && r._showSubMenuCore(n, r._getSubMenuPosition(t))
        }, _getSubMenuPosition: function (n) {
            var f, i, r, t, u;
            r = !!this.option("rtlEnabled"), i = this.option("submenuDirection").toLowerCase(), u = n.parent(".dx-menu-item-wrapper"), t = {collision: "flip", of: u, offset: {h: 0, v: -1}};
            switch (i) {
                case"left":
                    t.at = "left top", t.my = "right top";
                    break;
                case"right":
                    t.at = "right top", t.my = "left top";
                    break;
                default:
                    r ? (t.at = "left top", t.my = "right top") : (t.at = "right top", t.my = "left top")
            }
            return t
        }, _isSubMenuVisible: function (n) {
            return n.css("display") !== "none"
        }, _animate: function (n, t) {
            s.animate(n, t)
        }, _stopAnimate: function (n) {
            s.stop(n, !0)
        }, _showSubMenuCore: function (t, i) {
            var r = this.option("animation") ? this.option("animation").show : {};
            this._overlay && this._overlay.option("visible") && (l.isDefined(this._shownSubMenus) || (this._shownSubMenus = []), n.inArray(t, this._shownSubMenus) && this._shownSubMenus.push(t), t.css("display", "block"), DevExpress.position(t, i), this._stopAnimate(t), this._animate(t, r))
        }, _hideSubMenuCore: function (t) {
            var i = n.inArray(t, this._shownSubMenus), r = this.option("animation") ? this.option("animation").hide : {};
            i >= 0 && this._shownSubMenus.splice(i, 1), this._stopAnimate(t), this._animate(t, r.hide), t.css("display", "none")
        }, _updateOverlayVisibilityOnClick: function (t) {
            var i, r, u;
            if (t.args.length && t.args[0]) {
                if (t.args[0].jQueryEvent.stopPropagation(), i = t.args[0].itemElement, r = i.children(".dx-submenu"), i.context === r.context && r.is(":visible"))
                    return;
                if (!i.data(this._itemDataKey()) || i.data(this._itemDataKey()).disabled)
                    return;
                this._updateSelectedItemOnClick(i, t.args[0].itemData), r.length === 0 ? (u = n(i.parents(".dx-submenu")[0]), this._hideChildrenSubMenus(u), !t.canceled && this._overlay && this._overlay.option("visible") && this.option("visible", !1)) : (this._shownSubMenus && this._shownSubMenus.length > 0 && (this._shownSubMenus[0].is(r) || this._shownSubMenus[0].has(r).length === 1 ? this._hideChildrenSubMenus(r) : this._hideAllShownSubMenus()), this._showSubMenuGroup(r, i))
            }
        }, _hideChildrenSubMenus: function (t) {
            var i = this, r = n.extend([], i._shownSubMenus);
            n.each(r, function (n, r) {
                (t.is(r) || t.has(r).length) && i._hideSubMenuCore(r)
            })
        }, _hideAllShownSubMenus: function () {
            var t = this, i = n.extend([], t._shownSubMenus);
            n.each(i, function (n, i) {
                t._hideSubMenuCore(i)
            })
        }, show: function (n) {
            var t = this.option("position"), i, r;
            return n && n.preventDefault && (t = {at: "top left", my: "top left", of: n}), t.of || (t.of = this._target), i = {position: t, jQueryEvent: n}, r = this._createActionByOption("positioningAction", i), r(i), !i.canceled && this._overlay && (t && (this._overlay.option("position", null), this._overlay.option("position", t)), this._overlay.toggle(!0), this._setOptionInternal("visible", !0)), this.option("visible")
        }, hide: function () {
            this._overlay && (this._overlay.toggle(!1), this._setOptionInternal("visible", !1))
        }}))
}(jQuery, DevExpress), function (n, t, i) {
    var o = t.ui, c = t.utils, u = o.events, b = t.fx, k = "<div />", d = "<ul />", g = "dxMenu", nt = 50, r = "dx-menu", l = r + "-vertical", a = r + "-horizontal", f = r + "-item", v = f + "-selected", y = r + "-items-container", s = f + "-with-submenu", p = f + "-wrapper", h = "dx-context-menu", w = r + "-separator", e = h + "-container-border";
    t.registerComponent("dxMenu", o.dxMenuBase.inherit({_deprecatedOptions: {firstSubMenuDirection: {since: "14.1", message: "Use the 'submenuDirection' option instead."}, showPopupMode: {since: "14.1", message: "Use the 'showFirstSubmenuMode' option instead."}, allowSelectItem: {since: "14.1", message: "Use the 'allowSelection' option instead."}}, _optionAliases: {firstSubMenuDirection: "submenuDirection", showPopupMode: "showFirstSubmenuMode", allowSelectItem: "allowSelection"}, _setDefaultOptions: function () {
            this.callBase(), this.option({orientation: "horizontal", submenuDirection: "auto", firstSubMenuDirection: "auto", showFirstSubmenuMode: "onclick", showPopupMode: "onclick", showSubmenuMode: "auto", items: [], hoverStateEnabled: !0, allowSelection: !0, allowSelectItem: !0, allowSelectOnClick: !0, selectedItem: null, cssClass: "", animation: {show: {type: "fade", from: 0, to: 1, duration: 100}, hide: {type: "fade", from: 1, to: 0, duration: 100}}, submenuShowingAction: null, submenuShownAction: null, submenuHidingAction: null, submenuHiddenAction: null, itemSelectAction: null})
        }, _optionsByReference: function () {
            return n.extend(this.callBase(), {animation: !0, selectedItem: !0})
        }, _render: function () {
            this.callBase(), this._element().addClass(r), this._addCustomCssClass(this._element()), this._attachItemHoverEvents()
        }, _renderItems: function (t) {
            var i = this, e, u, r, f, o, s, h;
            e = i.option("orientation") === "vertical", u = n("<div>").appendTo(i._element()), u.addClass(e ? l : a), r = n("<ul>").appendTo(u), r.addClass(y), r.css("min-height", this._getValueHeight(u)), n.each(t, function (t, u) {
                u && (i._renderSeparator(u, t, r), f = n("<li>").appendTo(r), f.addClass(p), o = i._renderItem(t, u, f), i._renderSelectedItem(u, f), i._renderChildrenItems(u, o))
            })
        }, _renderSeparator: function (t, i, r) {
            var u;
            t.beginGroup && i > 0 && (u = n("<li>").appendTo(r), u.addClass(w))
        }, _renderSelectedItem: function (n, t) {
            this.option("allowSelection") && n === this.selectedItem && (t.addClass(v), this.$selectedItemWrapper = t)
        }, _renderChildrenItems: function (n, t) {
            this._hasChildren(n) && (this._createPopupMenu(n.items, t, this.selectedItem).appendTo(t), this._renderBorderElement(t))
        }, _renderBorderElement: function (t) {
            var i = n("<div>").appendTo(t);
            i.addClass(e), i.css("display", "none")
        }, _getValueHeight: function (t) {
            var i = n("<div>").html("Jj").css({width: "auto", position: "fixed", top: "-3000px", left: "-3000px"}).appendTo(t), r = i.height();
            return i.remove(), r
        }, _handleItemMouseEnter: function (t) {
            var i = this, h = 300, e, r, f, o, s;
            if ((r = i._getItemElementByEventArgs(t), !r.data(this._itemDataKey()).disabled) && (i._renderItemHotTrack(r, !0), f = i._getPopupMenuByRootElement(r), o = i.option("showFirstSubmenuMode").toLowerCase(), o !== "onclick" && f))
                if (clearTimeout(i._hidePopupMenuTimer), clearTimeout(i._showPopupMenuTimer), s = i.option("showFirstSubmenuMode") !== "onhover", s && i.visiblePopupMenu == f && !f._overlay.option("visible")) {
                    e = u.addNamespace("mousemove", i.NAME);
                    r.off(e).on(e, n.proxy(i._handleItemMouseMove, i));
                    i._showPopupMenuTimer = setTimeout(function () {
                        i._showPopupMenu(f, r)
                    }, h)
                } else
                    i._showPopupMenu(f, r)
        }, _handleItemMouseLeave: function (n) {
            var t = this, e = 50, o = 300, i, r, u, f;
            (u = t.option("showFirstSubmenuMode").toLowerCase(), f = t.option("showFirstSubmenuMode") !== "onhover" ? o : e, i = t._getItemElementByEventArgs(n), i.data(this._itemDataKey()).disabled) || (t._renderItemHotTrack(i, !1), u !== "onclick" && (clearTimeout(t._showPopupMenuTimer), clearTimeout(t._hidePopupMenuTimer), t._hidePopupMenuTimer = setTimeout(function () {
                r = t._getPopupMenuByRootElement(i), t._hidePopupMenu(r), t.visiblePopupMenu == r && (t.visiblePopupMenu = null)
            }, f)))
        }, _handleItemMouseMoveCore: function (n) {
            var t = this, r = 300, i;
            t._showPopupMenuTimer && (clearTimeout(t._hidePopupMenuTimer), clearTimeout(t._showPopupMenuTimer), i = t._getPopupMenuByRootElement(n), t._showPopupMenuTimer = setTimeout(function () {
                t._showPopupMenu(i, n)
            }, r))
        }, _updateOverlayVisibilityOnClick: function (n) {
            var r = this, t, i;
            if (n.args.length && n.args[0]) {
                if (n.args[0].jQueryEvent.stopPropagation(), t = n.args[0].itemElement, t.data(this._itemDataKey()).disabled)
                    return;
                i = r._getPopupMenuByRootElement(t), this._updateSelectedItemOnClick(t, n.args[0].itemData), i && (i._overlay.option("visible") ? r.option("showFirstSubmenuMode") === "onclick" && r._hidePopupMenu(i) : r._showPopupMenu(i, t))
            }
        }, _createPopupMenu: function (t, i, r) {
            var o = this, f, e = n("<div>"), a = n("<div>"), s, c, l;
            c = u.addNamespace("mouseenter", o.NAME + "_popupMenu"), l = u.addNamespace("mouseleave", o.NAME + "_popupMenu"), e.dxContextMenu({_templates: this.option("_templates"), target: a, items: t, position: o._getPopupMenuPosition(i), showAdditionalElements: !0, selectedItem: r, cssClass: this.option("cssClass"), animation: this.option("animation"), rtlEnabled: this.option("rtlEnabled"), disabled: this.option("disabled"), showSubmenuMode: this.option("showSubmenuMode") === "auto" ? this.option("showFirstSubmenuMode") : this.option("showSubmenuMode"), itemSelectAction: n.proxy(this._nestedItemSelectActionHandler, this), itemClickAction: n.proxy(this._nestedItemClickActionHandler, this), showingAction: n.proxy(this._contextMenuShowingActionHandler, this, i, f), shownAction: n.proxy(this._contextMenuShownActionHandler, this, i, f), hidingAction: n.proxy(this._contextMenuHidingActionHandler, this, i, f), hiddenAction: n.proxy(this._contextMenuHiddenActionHandler, this, i, f)}), e.addClass(h), f = e.dxContextMenu("instance"), s = f._overlay.content();
            s.off(c).on(c, null, n.proxy(this._handlePopupMenuMouseEnter, this, i));
            s.off(l).on(l, null, n.proxy(this._handlePopupMenuMouseLeave, this, i));
            return e
        }, _getPopupMenuPosition: function (n) {
            var i, u, r, t;
            r = !!this.option("rtlEnabled"), i = this.option("orientation").toLowerCase() == "vertical", u = this.option("submenuDirection").toLowerCase(), t = {collision: "flip", of: n};
            switch (u) {
                case"leftOrTop":
                    t.at = i ? "left top" : "left top", t.my = i ? "right top" : "left bottom";
                    break;
                case"rightOrBottom":
                    t.at = i ? "right top" : "left bottom", t.my = i ? "left top" : "left top";
                    break;
                case"auto":
                default:
                    i ? (t.at = r ? "left top" : "right top", t.my = r ? "right top" : "left top") : (t.at = r ? "right bottom" : "left bottom", t.my = r ? "right top" : "left top")
            }
            return t
        }, _nestedItemSelectActionHandler: function (n) {
            this._updateSelectedItemOnClick(n.itemElement, n.itemData)
        }, _nestedItemClickActionHandler: function (n) {
            var t = this._createActionByOption("itemClickAction", {});
            t(n)
        }, _contextMenuShowingActionHandler: function (n, r) {
            var f, u, o;
            f = this._createActionByOption("submenuShowingAction", {}), f({rootItem: n, popupMenu: r}), u = n.children("." + e), this._options.width !== i && (this._options.rtlEnabled ? u.width(this._$element.width() - n.position().right) : u.width(this._$element.width() - n.position().left)), u.css("display", "block"), t.fx.stop(u, !0), o = this.option("animation") ? this.option("animation").show : {}, t.fx.animate(u, o), n.addClass(s)
        }, _contextMenuShownActionHandler: function (n, t) {
            var i = this._createActionByOption("submenuShownAction", {});
            i({rootItem: n, popupMenu: t})
        }, _contextMenuHidingActionHandler: function (n, i) {
            var u, r, f;
            u = this._createActionByOption("submenuHidingAction", {}), u({rootItem: n, popupMenu: i}), r = n.children("." + e), f = this.option("animation") ? this.option("animation").hide : {}, t.fx.animate(r, f), r.css("display", "none"), n.removeClass(s)
        }, _contextMenuHiddenActionHandler: function (n, t) {
            var i = this._createActionByOption("submenuHiddenAction", {});
            i({rootItem: n, popupMenu: t})
        }, _handlePopupMenuMouseEnter: function (n) {
            this._hoveredPopupMenuContainer = n
        }, _handlePopupMenuMouseLeave: function (n) {
            var t = this, i, r = 50;
            setTimeout(function () {
                t._hoveredPopupMenuContainer && t._hoveredPopupMenuContainer.is(t._hoveredRootItem) || (i = t._getPopupMenuByRootElement(n), i && i.hide()), t._hoveredPopupMenuContainer = null
            }, r)
        }, _showPopupMenu: function (n, t) {
            c.isDefined(this.visiblePopupMenu) && this.visiblePopupMenu !== n && this.visiblePopupMenu.hide(), this._renderItemHotTrack(t, !1), n.show(), this.visiblePopupMenu = n, this._hoveredRootItem = t
        }, _hidePopupMenu: function (n) {
            this._hoveredRootItem && this._hoveredRootItem.is(this._hoveredPopupMenuContainer) || n && n.hide(), this._hoveredRootItem = null
        }, _optionChanged: function (n, t) {
            if (!this._optionChangedInternalFlag) {
                this.visiblePopupMenu && this.visiblePopupMenu.hide();
                switch (n) {
                    case"items":
                        this.selectedItem = this._getLastSelectedItem(t), this._setOptionInternal("selectedItem", this.selectedItem), this.callBase.apply(this, arguments);
                        break;
                    case"selectedItem":
                        this.selectedItem = t;
                        break;
                    default:
                        this.callBase.apply(this, arguments)
                    }
            }
        }, _getPopupMenuByRootElement: function (n) {
            var i = null, t;
            return n && (t = n.children(".dx-context-menu"), t.length > 0 && (i = t.dxContextMenu("instance"))), i
        }, _containNode: function (t, i) {
            var f = this, u = !1, r = !1;
            return n.each(t, function (n, t) {
                t == i && (r = !0), t.items && (u = f._containNode(t.items, i), u ? r = u : r)
            }), r
        }}))
}(jQuery, DevExpress), function (n, t, i) {
    var u = t.ui, f = t.utils, e = u.events, o = t.fx, l = t.translator, a = "dx-overlay", v = "dx-overlay-wrapper", y = "dx-overlay-content", p = "dx-overlay-shader", w = "dx-overlay-modal", b = "dx-rtl", s = ["showingAction", "shownAction", "hidingAction", "hiddenAction", "positioningAction", "positionedAction"], k = 1e3, r = [], d = "dx-state-disabled", h = t.devices.real(), g = h.platform === "android" && /^4\.0(\.\d)?/.test(h.version.join(".")) && navigator.userAgent.indexOf("Chrome") === -1, nt = function (n) {
        if (n.width(), g) {
            var t = n.parents(), i = t.is(".dx-scrollable-native");
            i || (t.css("backface-visibility", "hidden"), t.css("backface-visibility"), t.css("backface-visibility", "visible"))
        }
    }, c = function (t) {
        return n(t instanceof n.Event ? t.target : t)
    };
    t.registerComponent("dxOverlay", u.Widget.inherit({_setDefaultOptions: function () {
            this.callBase(), this.option({activeStateEnabled: !1, visible: !1, deferRendering: !0, shading: !0, shadingColor: "", position: {my: "center", at: "center", of: window}, width: function () {
                    return n(window).width() * .8
                }, height: function () {
                    return n(window).height() * .8
                }, animation: {show: {type: "pop", duration: 400}, hide: {type: "pop", duration: 400, to: {opacity: 0, scale: 0}, from: {opacity: 1, scale: 1}}}, closeOnOutsideClick: !1, showingAction: null, shownAction: null, hidingAction: null, hiddenAction: null, contentTemplate: "template", targetContainer: i, hideTopOverlayHandler: i, closeOnTargetScroll: !1, positioningAction: null, positionedAction: null})
        }, _defaultOptionsRules: function () {
            return this.callBase().slice(0).concat([{device: function () {
                        var r = t.devices.real(), u = r.platform, i = r.version;
                        return u === "android" && (i[0] < 4 || i[0] == 4 && i[1] <= 1)
                    }, options: {animation: {show: {type: "fade", duration: 400}, hide: {type: "fade", duration: 400, to: {opacity: 0}, from: {opacity: 1}}}}}])
        }, _optionsByReference: function () {
            return n.extend(this.callBase(), {animation: !0})
        }, _wrapper: function () {
            return this._$wrapper
        }, _container: function () {
            return this._$container
        }, _init: function () {
            this.callBase(), this._initActions(), this._initCloseOnOutsideClickHandler(), this._$wrapper = n("<div>").addClass(v), this._$container = n("<div>").addClass(y);
            var t = this._element();
            this._$wrapper.addClass(t.attr("class")), t.addClass(a);
            this._$wrapper.on("MSPointerDown", n.noop)
        }, _clean: n.noop, _initOptions: function (n) {
            this._initTargetContainer(n.targetContainer), this._initHideTopOverlayHandler(n.hideTopOverlayHandler), this.callBase(n)
        }, _initTargetContainer: function (r) {
            r = r === i ? t.overlayTargetContainer() : r;
            var f = this._element(), u = f.closest(r);
            u.length || (u = n(r).first()), this._$targetContainer = u.length ? u : f.parent()
        }, _targetContainer: function () {
            return this._$targetContainer
        }, _initHideTopOverlayHandler: function (t) {
            this._hideTopOverlayHandler = t !== i ? t : n.proxy(this._defaultHideTopOverlayHandler, this)
        }, _defaultHideTopOverlayHandler: function () {
            this.hide()
        }, _initActions: function () {
            this._actions = {}, n.each(s, n.proxy(function (t, i) {
                this._actions[i] = this._createActionByOption(i) || n.noop
            }, this))
        }, _visibilityChanged: function (n) {
            n && this._dimensionChanged()
        }, _dimensionChanged: function () {
            this._renderGeometry()
        }, _initCloseOnOutsideClickHandler: function () {
            this._documentDownHandler = n.proxy(function () {
                this._handleDocumentDown.apply(this, arguments)
            }, this)
        }, _handleDocumentDown: function (t) {
            var i, u, f;
            r[r.length - 1] === this._zIndex && (i = this.option("closeOnOutsideClick"), n.isFunction(i) && (i = i(t)), i && (u = this._$container, f = !u.is(t.target) && !n.contains(u.get(0), t.target), f && (t.preventDefault(), this.hide())))
        }, _renderVisibilityAnimate: function (t) {
            return t && (this._showTimestamp = n.now()), this._stopAnimation(), t ? this._makeVisible() : this._makeHidden()
        }, _updateRegistration: function (t) {
            var i, u;
            t ? this._zIndex || (i = r.length, this._zIndex = (i ? r[i - 1] : k) + 1, r.push(this._zIndex)) : this._zIndex && (u = n.inArray(this._zIndex, r), r.splice(u, 1), delete this._zIndex)
        }, _normalizePosition: function () {
            this._position = this.option("position")
        }, _makeVisible: function () {
            this._normalizePosition();
            var r = this, i = n.Deferred(), u = r.option("animation") || {}, t = u.show, f = t && t.complete || n.noop;
            return this._updateRegistration(!0), t && t.to && (t = n.extend({type: "slide"}, t), n.extend(t.to, {position: this._position})), this._isHidingActionCancelled ? (delete this._isHidingActionCancelled, i.resolve()) : (this._actions.showingAction(), this._$wrapper.css("z-index", this._zIndex), this._$container.css("z-index", this._zIndex), this._toggleVisibility(!0), this._animate(t, function () {
                f.apply(this, arguments), r._actions.shownAction(), i.resolve()
            })), i.promise()
        }, _makeHidden: function () {
            var t = this, i = n.Deferred(), f = this.option("animation") || {}, r = f.hide, e = r && r.complete || n.noop, u = {cancel: !1};
            return this._actions.hidingAction(u), u.cancel ? (this._isHidingActionCancelled = !0, this.option("visible", !0), i.resolve()) : (this._toggleShading(!1), this._animate(r, function () {
                t._toggleVisibility(!1), e.apply(this, arguments), t._updateRegistration(!1), t._actions.hiddenAction(), i.resolve()
            })), i.promise()
        }, _animate: function (t, i) {
            t ? o.animate(this._$container, n.extend({}, t, {complete: i})) : i()
        }, _stopAnimation: function () {
            o.stop(this._$container, !0)
        }, _toggleVisibility: function (n) {
            this._stopAnimation(), n || f.triggerHidingEvent(this.content()), this.callBase.apply(this, arguments), this._$container.toggle(n), this._toggleShading(n), n ? (this._renderContent(), this._moveToTargetContainer(), this._renderGeometry(), f.triggerShownEvent(this.content())) : this._moveFromTargetContainer(), this._toggleSubscriptions(n), this._updateRegistration(n)
        }, _toggleShading: function (n) {
            this._$wrapper.toggleClass(w, this.option("shading") && !this.option("targetContainer")), this._$wrapper.toggleClass(p, n && this.option("shading")), this._$wrapper.css("background-color", this.option("shading") ? this.option("shadingColor") : "")
        }, _toggleSubscriptions: function (n) {
            this._toggleHideTopOverlayCallback(n), this._toggleDocumentDownHandler(n), this._toggleParentsScrollSubscription(n)
        }, _toggleHideTopOverlayCallback: function (n) {
            this._hideTopOverlayHandler && (n ? t.hideTopOverlayCallback.add(this._hideTopOverlayHandler) : t.hideTopOverlayCallback.remove(this._hideTopOverlayHandler))
        }, _toggleDocumentDownHandler: function (t) {
            var r = this, i = e.addNamespace("dxpointerdown", r.NAME);
            if (t)
                n(document).on(i, this._documentDownHandler);
            else
                n(document).off(i, this._documentDownHandler)
        }, _toggleParentsScrollSubscription: function (n) {
            var i = this._position;
            if (i && i.of) {
                var t = this, r = t.option("closeOnTargetScroll"), u = e.addNamespace("scroll", t.NAME), f = c(i.of).parents().add(window);
                t._proxiedTargetScrollHandler = t._proxiedTargetScrollHandler || function () {
                    return t._targetScrollHandler.apply(t, arguments)
                }, f[n && r ? "on" : "off"](u, t._proxiedTargetScrollHandler)
            }
        }, _targetScrollHandler: function (t) {
            var i = this.option("closeOnTargetScroll"), r = !1;
            n.isFunction(i) && (r = i(t)), r || this.hide()
        }, _renderContent: function () {
            this._contentAlreadyRendered || !this.option("visible") && this.option("deferRendering") || (this._contentAlreadyRendered = !0, this.callBase())
        }, _renderContentImpl: function (n) {
            var t = this._element(), i = n || this._getTemplate(this.option("contentTemplate"));
            this._$container.append(t.contents()).appendTo(t), i && i.render(this.content())
        }, _fireContentReadyAction: function () {
            this.option("visible") && this._moveToTargetContainer(), this.callBase.apply(this, arguments)
        }, _moveFromTargetContainer: function () {
            this._$container.appendTo(this._element()), this._detachWrapperToTargetContainer()
        }, _detachWrapperToTargetContainer: function () {
            this._$wrapper.detach()
        }, _moveToTargetContainer: function () {
            this._attachWrapperToTargetContainer(), this._$container.appendTo(this._$wrapper)
        }, _attachWrapperToTargetContainer: function () {
            var n = this._element();
            !this._$targetContainer || this._$targetContainer[0] === n.parent()[0] ? this._$wrapper.appendTo(n) : this._$wrapper.appendTo(this._$targetContainer)
        }, _renderGeometry: function () {
            this.option("visible") && this._renderGeometryImpl()
        }, _renderGeometryImpl: function () {
            this._stopAnimation(), this._normalizePosition(), this._renderShading(), this._renderDimensions(), this._renderPosition()
        }, _renderShading: function () {
            var n = this._$wrapper, t = this._getTargetContainer();
            n.css("position", t.get(0) === window ? "fixed" : "absolute"), this.option("shading") && n.show(), this._renderShadingDimensions(), this._renderShadingPosition()
        }, _renderShadingPosition: function () {
            if (this.option("shading")) {
                var n = this._getTargetContainer();
                t.position(this._$wrapper, {my: "top left", at: "top left", of: n})
            }
        }, _renderShadingDimensions: function () {
            if (this.option("shading")) {
                var n = this._getTargetContainer();
                this._$wrapper.css({width: n.outerWidth(), height: n.outerHeight()})
            }
        }, _getTargetContainer: function () {
            var n = this._position, t = this.option("targetContainer"), i = n ? n.of : null;
            return c(t || i)
        }, _renderDimensions: function () {
            this._$container.outerWidth(this.option("width")).outerHeight(this.option("height"))
        }, _renderPosition: function () {
            var i, n, r;
            l.resetPosition(this._$container), i = this._position, n = t.calculatePosition(this._$container, i), this._actions.positioningAction({position: n}), r = t.position(this._$container, n), this._actions.positionedAction({position: r}), nt(this._$container)
        }, _dispose: function () {
            this._stopAnimation(), this._toggleSubscriptions(!1), this._updateRegistration(!1), this._actions = null, this.callBase(), this._$wrapper.remove(), this._$container.remove()
        }, _toggleDisabledState: function (n) {
            this.callBase.apply(this, arguments), this._$container.toggleClass(d, n)
        }, _toggleRTLDirection: function (n) {
            this._$container.toggleClass(b, n)
        }, _optionChanged: function (t, i) {
            if (n.inArray(t, s) > -1) {
                this._initActions();
                return
            }
            switch (t) {
                case"shading":
                case"shadingColor":
                    this._toggleShading(this.option("visible"));
                    break;
                case"width":
                case"height":
                case"position":
                    this._renderGeometry();
                    break;
                case"visible":
                    this._renderVisibilityAnimate(i).done(n.proxy(function () {
                        this._animateDeferred && (this._animateDeferred.resolveWith(this), delete this._animateDeferred)
                    }, this));
                    break;
                case"targetContainer":
                    this._initTargetContainer(i), this._invalidate();
                    break;
                case"deferRendering":
                case"contentTemplate":
                    this._invalidate();
                    break;
                case"closeOnOutsideClick":
                    this._toggleDocumentDownHandler(this.option("visible"));
                    break;
                case"closeOnTargetScroll":
                    this._toggleParentsScrollSubscription(this.option("visible"));
                    break;
                case"animation":
                    break;
                case"rtlEnabled":
                    this._toggleRTLDirection(i);
                    break;
                default:
                    this.callBase.apply(this, arguments)
                }
        }, toggle: function (t) {
            if (t = t === i ? !this.option("visible") : t, t === this.option("visible"))
                return n.Deferred().resolve().promise();
            var r = n.Deferred();
            return this._animateDeferred = r, this.option("visible", t), r.promise()
        }, show: function () {
            return this.toggle(!0)
        }, hide: function () {
            return this.toggle(!1)
        }, content: function () {
            return this._$container
        }, repaint: function () {
            this._renderGeometry()
        }}))
}(jQuery, DevExpress), DevExpress.MOD_TMP_WIDGETS_FOR_EXPORTER = !0);
DevExpress.MOD_TMP_EXPORTER || (function (n, t) {
    var h = n.ui, c = n.utils, i = "file", l = "body", e = "exportTo", o = "print", a = "dx-non-printable", v = "dx-printable", r = ["PDF", "PNG", "SVG"], s = ["JPEG", "GIF"].concat(r), u = n.viz.core, f = n.DOMComponent.inherit({_normalizeHtml: u.BaseWidget.prototype._normalizeHtml, _killTracker: u.BaseWidget.prototype._killTracker, _getSvgElements: function () {
            var n = this, i = [];
            return t(n.getsourceContainer()).find("svg").each(function (r) {
                i[r] = n._normalizeHtml(t(this).clone().wrap("<div><\/div>").parent().html())
            }), JSON.stringify(i)
        }, _appendTextArea: function (n, i, r) {
            t("<textarea/>", {id: n, name: n, val: i}).appendTo(r)
        }, _formSubmit: function (n) {
            n.submit(), n.remove()
        }, _setDefaultOptions: function () {
            this.callBase(), this.option({redrawOnResize: !1, menuAlign: "right", exportFormat: r, printingEnabled: !0, fileName: i, showMenu: !0})
        }, _createWindow: function () {
            return window.open("", "printDiv", "")
        }, _createExportItems: function (n) {
            var r = this;
            return t.map(n, function (n) {
                return(n = n.toUpperCase(), t(r.getsourceContainer()).find("svg").length > 1 && n === "SVG") ? null : t.inArray(n.toUpperCase(), s) === -1 ? null : {name: n, text: n + " " + i}
            })
        }, getsourceContainer: function () {
            var n = this.option("sourceContainer") || this.option("sourceContainerId");
            return t(n)
        }, _render: function () {
            var n = this, u = n.option("fileName"), f = n._createExportItems(n.option("exportFormat")), i = t("<div />"), r = [{name: "export", icon: e, items: f}], s = {align: n.option("menuAlign"), items: r, itemClickAction: function (t) {
                    switch (t.itemData.name) {
                        case"print":
                            n.print();
                            break;
                        case"export":
                            break;
                        default:
                            n.exportTo(u, t.itemData.name)
                        }
                }};
            n.option("showMenu") && (n.option("printingEnabled") && r.push({icon: o, name: "print", click: function () {
                    n.print()
                }}), i.dxMenu(s), n._$element.empty(), n._$element.append(i))
        }, print: function () {
            var i = this.getsourceContainer().html(), n = this._createWindow();
            t(n.document.body).html(i), n.document.close(), n.focus(), n.print(), n.close()
        }, exportTo: function (n, i) {
            var r = this, f = r.getsourceContainer(), u = t("<form/>", {method: "POST", action: r.option("serverUrl"), enctype: "application/x-www-form-urlencoded", target: "_self", css: {display: "none", visibility: "hidden"}});
            r._appendTextArea("exportContent", f.clone().wrap("<div><\/div>").parent().html(), u), r._appendTextArea("svgElements", r._getSvgElements(), u), r._appendTextArea("fileName", n, u), r._appendTextArea("format", i.toLowerCase(), u), r._appendTextArea("width", f.width(), u), r._appendTextArea("height", f.height(), u), r._appendTextArea("url", window.location.host, u), t(document.body).append(u), r._formSubmit(u)
        }});
    t.extend(!0, n, {exporter: {Exporter: f}}), n.registerComponent("dxExporter", f)
}(DevExpress, jQuery), DevExpress.MOD_TMP_EXPORTER = !0);