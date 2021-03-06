"use strict";
var colors = d3.scaleOrdinal().domain(30).range(d3.schemeCategory10);
var _typeof = typeof Symbol === "function" && typeof Symbol.iterator === "symbol" ? function (t) {
    return typeof t
} : function (t) {
    return t && typeof Symbol === "function" && t.constructor === Symbol && t !== Symbol.prototype ? "symbol" : typeof t
};
(function () {
    var F = {
        graph: {
            layout_mode: "force",
            analyse_mode: false,
            gravitation: .03,
            repulsion: -400,
            calculate_state: true
        },
        node: {node_scale: 1, node_size: 25, node_color: "black", node_opacity: "1", fill: "label"},
        link: {link_scale: 1, link_width: 2, link_color: "#00FFFB"},
        show_status: {node_text: true, link_text: true}
    };
    var X = -1;
    var t = {
        nodes: [{
            id: 0,
            label: "Person",
            size: 30,
            properties: {role: "自然人股东", name: "刘德", image: "http://148.70.238.152/static/image/github.png"}
        }, {
            id: 1,
            label: "Company",
            size: 10,
            properties: {registCapi: "5000.000", name: "有品信息科技有限公司", econKind: "有限责任公司(自然人投资或控股)", status: "存续"}
        }, {id: 2, size: 15, label: "Company", properties: {name: "有品信息科技有限公司"}}, {
            id: 3,
            label: "Company",
            properties: {registCapi: "5000.000"}
        }],
        links: [{type: "EMPLOY", source: 0, target: 1, properties: {role: "董事"}}, {
            type: "EMPLOY",
            source: 1,
            target: 2,
            properties: {role: "董事"}
        }, {type: "EMPLOY", source: 2, target: 3, properties: {role: "董事"}}, {
            type: "EMPLOY",
            source: 0,
            target: 2,
            properties: {role: "董事"}
        }]
    };

    function e() {
        return t
    }

    function n(t, e, n, M) {
        var y = {};
        Object.keys(F).forEach(function (t) {
            if (typeof e[t] !== "undefined") {
                y[t] = Object.assign({}, F[t], e[t])
            } else {
                y[t] = Object.assign({}, F[t])
            }
        });
        X += 1;
        var i = X;
        var a = {nodes: [], links: []};
        a = JSON.parse(JSON.stringify(typeof t !== "undefined" ? t : a));
        var r = typeof arguments[2] !== "undefined" ? n : et.width / 2;
        var o = typeof arguments[3] !== "undefined" ? M : et.height / 2;
        var s = d3.forceSimulation().alpha(1).alphaDecay(.002).alphaMin(.002).force("r", null).force("center", d3.forceCenter(r, o)).force("link", d3.forceLink().id(function (t) {
            return t.id
        }).strength(y.graph.gravitation)).force("charge", d3.forceManyBody().strength(y.graph.repulsion)).force("collision", d3.forceCollide(y.node.node_size)).on("tick", N).on("end", function () {
            Z()
        });
        var u = d3.forceSimulation().alpha(1).alphaDecay(.01).alphaMin(.002).force("r", d3.forceRadial(100, r, o)).force("center", d3.forceCenter(r, o)).force("link", d3.forceLink().id(function (t) {
            return t.id
        }).strength(0)).force("collision", d3.forceCollide(y.node.node_size)).on("tick", N).on("end", function () {
            Z()
        });
        var c = K.append("g").attr("id", "network_graph").style("user-select", "none").style("font-size", 7).style("text-anchor", "middle");
        q.call(d3.zoom().scaleExtent([.1, 10]).on("zoom", function () {
            K.attr("transform", d3.event.transform)
        })).on("dblclick.zoom", null);
        c.append("g").attr("id", "temp_layout");
        c.append("g").attr("id", "link_layout");
        c.append("g").attr("id", "node_layout");
        var l = c.append("g").attr("id", "defs_layout");
        l.append("pattern").attr("id", "pattern-0").append("image").attr("xlink:href", nt);
        l.append("pattern").attr("id", "pattern-1").append("image").attr("xlink:href", Mt);
        l.append("pattern").attr("id", "pattern-2").append("image").attr("xlink:href", rt);
        l.selectAll("pattern").attr("width", "100%").attr("height", "100%").select("image").attr("width", 20).attr("height", 20);
        var d = [];
        var L = [];
        U(a);

        function N() {
            d.attr("transform", function (t) {
                return "translate(" + t.x + "," + t.y + ")"
            });
            L.select(".link-path").attr("d", function (t) {
                return m(t)
            });
            L.select("text").attr("dx", function (t) {
                return Y(t)
            });
            if (tt.length !== 0) {
                tt.select(".link-path").attr("d", function (t) {
                    return m(t)
                });
                tt.selectAll(".link").select("text").attr("dx", function (t) {
                    return Y(t)
                })
            }
        }

        function j(t) {
            d = c.select("#node_layout").selectAll("g").data(t);
            d.exit().remove();
            var e = d.enter().append("g").attr("class", "node").attr("transform", function (t) {
                return "x" in t && "y" in t ? "translate(" + t.x + ", " + t.y + ")" : ""
            }).on("mousedown.select-node", w).on("mouseenter.enter-node", T).on("mouseleave.leave-node", x).on("dblclick", z).call(d3.drag().on("start", h).on("drag", C).on("end", E));
            e.append("circle").attr("class", "background-circle").style("cursor", "move").style("stroke-opacity", "0").style("stroke-width", "16px").style("stroke", "#68bdf6bf");
            e.append("circle").attr("class", "foreground-circle").style("fill", function(d){return colors(d.color)}).style("cursor", "move").style("stroke-width", "3px").style("stroke", "#18769f");
            d = d.merge(e);
            d.selectAll("circle").attr("r", function (t) {
                if (!("size" in t)) {
                    t["size"] = y.node.node_size
                }
                return t.size * y.node.node_scale
            });
            g()
        }

        function g() {
            d.select("text").remove();
            d.filter(function (t) {
                return typeof t.label !== "undefined" ? true : false
            }).append("text").text(function (t) {
                return (typeof t[y.node.fill] !== "undefined" ? t[y.node.fill] : t.label) + ""
            }).attr("x", function (t) {
                return D(d3.select(this), (typeof t[y.node.fill] !== "undefined" ? t[y.node.fill] : t.label) + "")
            }).attr("dy", ".35em").style("display", y.show_status.node_text === true ? "block" : "none").style("pointer-events", "none").style("fill", "white")
        }

        function D(t, e) {
//          var n = e.length;
//          if (n <= 4) {
                t.append("tspan").attr("x", 0).attr("y", 2).text(e)
//          } else {
//              var M = e.substring(0, 4);
//              var r = e.substring(4, 9);
//              var a = e.substring(9, n);
//              var i = -9;
//              var o = 2;
//              var s = 10;
//              if (n < 10) {
//                  i += 5;
//                  o += 5
//              } else {
//                  a = e.substring(9, 11) + "..."
//              }
//              t.text("");
//              t.append("tspan").attr("x", 0).attr("y", i).text(M);
//              t.append("tspan").attr("x", 0).attr("y", o).text(r);
//              t.append("tspan").attr("x", 0).attr("y", s).text(a)
//          }
        }

        function f(t, e) {
            t.forEach(function (t) {
                t["path_count"] = 1;
                t["path_index"] = 0;
                t["reverse"] = false
            });
            for (var n = 1; n < t.length; n++) {
                for (var M = 0; M < n; M++) {
                    if (t[M].source === t[n].source && t[M].target === t[n].target) {
                        t[n].path_count = t[M].path_count += 1;
                        t[n].path_index = t[n].path_count - 1
                    } else if (t[M].source === t[n].target && t[M].target === t[n].source) {
                        t[n].path_count = t[M].path_count += 1;
                        t[n].path_index = t[n].path_count - 1;
                        t[n].reverse = true
                    }
                }
            }
            var r = e.selectAll("g").data(t);
            r.exit().remove();
            var a = r.enter().append("g").attr("class", "link");
            a.append("path").attr("class", "link-path").style("fill", "none").style("cursor", "pointer").style("stroke", "#6f7071");
            a.append("marker").attr("class", "link-marker").attr("markerUnits", "userSpaceOnUse").attr("viewBox", "0 -50 100 100").attr("refY", 0).attr("markerWidth", 12).attr("markerHeight", 12).attr("orient", "auto").append("path").attr("d", "M20,0 L0,-30 L90,0 L0,30 L20,0").style("fill", "#000000");
            a.append("text").attr("class", "link-text").attr("dx", function (t) {
                return Y(t)
            }).attr("dy", 4).append("textPath").style("font-size", "10px").style("fill", "#000000")
            .style("display", y.show_status.link_text === true ? "block" : "none")
            .attr("x",function(d){
    			return (d.source.x+d.target.x)/2;
    		})
    		.attr("y",function(d){
    			return (d.source.y+d.target.y)/2;
    		});
//          .attr("transform",function(d){
//          	let x1 = d.source.x,y1=d.source.y;
//          	let	x2 = d.target.x,y2=d.target.y;
//          	let width = x2-x1,height = y2-y1;
//          	let angle = Math.atan(height/width)*180 / Math.PI;
//          	return `translate(${(x1=x2)/2},${(y1+y2)/2})rotate(${angle})`;
//          });
            r = r.merge(a);
            r.select("marker").attr("id", function (t) {
                return "marker-" + i + "-" + t.index
            }).attr("refX", function (t) {
                return t.target.size * 10 + 70
            });
            r.select(".link-path").attr("id", function (t) {
                return "link" + i + "-" + t.index
            }).attr("d", function (t) {
                return m(t)
            }).attr("marker-end", function (t) {
                return "url(#marker-" + i + "-" + t.index + ")"
            }).style("stroke-width", 1).on("mousedown.select-link", I).on("mouseover.hover-link", k);
            r.select("textPath").text(function (t) {
                return t.relation
            }).attr("href", function (t) {
                return "#link" + i + "-" + t.index
            });
            arguments[2] ? tt = r : L = r
        }

        function w(t) {
            Z();
            t["selected"] = true;
            d3.select(this).classed("selected", true)
        }

        function z(M) {
            Z();
            var t = (M.size ? M.size : y.node.size) + 8;
            var e = t + 30;
            var n = Math.cos(175 / 360 * Math.PI);
            var r = Math.sin(175 / 360 * Math.PI);
            var a = Math.cos(55 / 360 * Math.PI);
            var i = Math.sin(55 / 360 * Math.PI);
            var o = -(2 * a + 2 * Math.sqrt(3) * i);
            var s = Math.pow(a, 2) + 3 * Math.pow(i, 2) + 2 * Math.sqrt(3) * a * i - 3 * Math.pow(e, 2);
            var u = (Math.sqrt(Math.pow(o, 2) - 16 * s) - o) / 8;
            var c = Math.sqrt(Math.pow(e, 2) - Math.pow(u, 2));
            var l = n;
            var L = Math.sqrt(1 - Math.pow(l, 2));
            var N = "M" + t * n + ", -" + t * r + " " + "A" + t + ", " + t + " 0 0, 1 " + t * a + ", " + t * i + "L" + u + "," + c + " " + "A" + e + ", " + e + " 0 0, 0 " + e * l + ", -" + e * L + "L" + t * n + ",-" + t * r;
            d3.selectAll(".menu").remove();
            var j = d3.select(this).selectAll(".menu").data([0, 1, 2]).enter().append("g").attr("class", "menu");
            j.append("path").attr("id", function (t, e) {
                return "menu-path" + e
            }).attr("d", N).attr("transform", function (t, e) {
                return "rotate(" + e * 120 + " 0, 0)"
            }).style("fill", "#bcbcbc").style("stroke", "#000000").style("stroke-width", 0).on("mouseenter", function () {
                d3.select(this).style("stroke-width", 1).style("fill", "#bcbcbca1")
            }).on("mouseleave", function () {
                d3.select(this).style("stroke-width", 0).style("fill", "#bcbcbc")
            }).on("click.menu", function (t) {
                var e = d3.select(this.parentNode.parentNode).datum();
                if (t === 0) {
                    p(M)
                } else if (t === 1) {
                    var n = d3.select("#temp_layout").append("line").attr("x1", M.x).attr("y1", M.y).style("stroke", "#6f7071").style("stroke-width", "2px").style("opacity", "0");
                    q.on("mousemove.add-link", function () {
                        var t = b("#container");
                        var e = t.k;
                        n.attr("x2", (d3.event.x - et.x - t.x) / e).attr("y2", (d3.event.y - et.y - t.y) / e);
                        n.style("opacity", 1)
                    });
                    d.selectAll("circle").style("cursor", "pointer").on("click.add-link", function (t) {
                        n.remove();
                        q.on("mousemove.add-link", null);
                        d.selectAll("circle").style("cursor", "move").on("click.add-link", null);
                        R(e, t, "TEST")
                    })
                } else if (t === 2) {
                    W(M)
                }
            });
            j.append("rect").attr("class", "menu-icon").attr("id", function (t, e) {
                return "menu-icon-" + e
            }).attr("width", "20").attr("height", "20").style("fill", function (t, e) {
                return "url(#pattern-" + e + ")"
            }).style("pointer-events", "none");
            d3.select("#menu-icon-0").attr("transform", "translate(" + (e - 28) + ", -" + 6 * e / 16 + ")");
            d3.select("#menu-icon-1").attr("transform", "translate(-10," + (e - 28) + ")");
            d3.select("#menu-icon-2").attr("transform", "translate(" + (7 - e) + ", -" + 6 * e / 16 + ")")
        }

        function T(t) {
            d3.select(this).select(".background-circle").style("stroke-opacity", .8)
        }

        function x(t) {
            d3.select(this).select(".background-circle").style("stroke-opacity", 0)
        }

        function p(t) {
            console.log(t)
        }

        function I() {
        }

        function k() {
        }

        var S = false, O = [], A = [];

        function h(t) {
            d3.event.sourceEvent.stopPropagation();
            Z();
            O = d.filter(function (t) {
                return t.selected
            });
            A = L.filter(function (e) {
                var n = false;
                O.each(function (t) {
                    if (t.id === e.source.id || t.id === e.target.id) {
                        n = true
                    }
                });
                return n
            })
        }

        function C(t) {
            O.attr("transform", function (t) {
                return "translate(" + (t.x += d3.event.dx) + "," + (t.y += d3.event.dy) + ")"
            });
            A.select("path").attr("d", function (t) {
                return m(t)
            });
            A.select("text").attr("dx", function (t) {
                return Y(t)
            });
            if (S === false) {
                v(d, O);
                v(L, A);
                S = true
            }
            if (tt.length !== 0) {
                tt.select(".link-path").attr("d", function (t) {
                    return m(t)
                });
                tt.select("text").attr("dx", function (t) {
                    return Y(t)
                })
            }
        }

        function E(t) {
            d3.event.sourceEvent.stopPropagation();
            if (!d3.event.sourceEvent.ctrlKey) {
                t.selected = false;
                d3.select(this).classed("selected", false)
            }
            d.style("opacity", 1);
            L.style("opacity", 1);
            S = false
        }

        function v(t, e) {
            t.style("opacity", .3);
            e.style("opacity", 1)
        }

        function m(t) {
            var e = "";
            var n = t.source.x;
            var M = t.source.y;
            var r = t.target.x;
            var a = t.target.y;
            if (t.source === t.target) {
                var i = 30 / t.path_count;
                e = "M" + n + ", " + M + "A" + i + ", " + i + " 0 1,1 " + r + ", " + (a + 1)
            } else if (t.path_count % 2 === 1 && t.path_index === 0) {
                e = "M" + n + "," + M + " L" + r + "," + a
            } else {
                var o = t.target.x - t.source.x;
                var s = t.target.y - t.source.y;
                var u = t.path_index;
                if (t.path_count % 2 === 1) {
                    u -= 1
                }
                var c = Math.sqrt(o * o + s * s) * Math.ceil((u + 1) / 2);
                var l = Math.pow(-1, t.path_index) === -1 ? 0 : 1;
                if (t.reverse === true) {
                    l = l === 0 ? 1 : 0
                }
                e = "M" + n + "," + M + "A" + c + "," + c + " 0 0, " + l + " " + r + "," + a
            }
            return e
        }

        function Y(t) {
            var e = t.source.x;
            var n = t.source.y;
            var M = t.target.x;
            var r = t.target.y;
            var a = Math.sqrt(Math.pow(M - e, 2) + Math.pow(r - n, 2));
            return a / 2
        }

        function Q(t) {
            var e = t.source.x;
            var n = t.source.y;
            var M = t.target.x;
            var r = t.target.y;
            var a = e < M ? 0 : 180;
            return "rotate(" + a + ", " + (e + M) / 2 + " " + (n + r) / 2 + ")"
        }

        function b(t) {
            var e = d3.select(t).attr("transform");
            var n = e && /translate/.test(e) && /scale/.test(e) && e.match(/translate\(([^\)]+)\)\s?scale\(([^\)]+)/);
            var M = n && n[1].split(",") || [0, 0];
            var r = n && n[2] || 1;
            var a = e && /rotate/.test(e) && e.match(/\s?rotate\(([^\)]+)/);
            var i = a && a[1] || 0;
            var o = M[0];
            var s = M[1];
            return {x: o, y: s, k: r, rotate: i}
        }

        function U(t) {
            a = t;
            if (y.graph.layout_mode === "force") {
                s.nodes(t.nodes).force("link").links(t.links)
            } else if (y.graph.layout_mode === "radius") {
                u.nodes(t.nodes).force("link").links(t.links)
            }
            j(t.nodes);
            f(t.links, c.select("#link_layout"));
            _()
        }

        function P() {
            return a
        }

        function _() {
//          y.graph.calculating = true;
//          d3.select("#network-status").style("animation", "calc ease 1s infinite");
//          s.alpha(0.5).restart()
        }

        function Z() {
            y.graph.calculating = false;
            d3.select("#network-status").style("animation", "none");
            s.stop()
        }

        function B(t) {
            y.graph.layout_mode = t
        }

        function G(t) {
            y.graph.analyse_mode = t
        }

        function H(t, e, n) {
            Z();
            t["x"] = arguments[1] ? e : et.width / 2;
            t["y"] = arguments[2] ? n : et.height / 2;
            a.nodes.push(t);
            U(a)
        }

        function R(e, n, t) {
            Z();
            var M = e, r = n;
            if ((typeof e === "undefined" ? "undefined" : _typeof(e)) !== "object") {
                d.each(function (t) {
                    if (t.id == e) {
                        M = t
                    }
                })
            }
            if ((typeof n === "undefined" ? "undefined" : _typeof(n)) !== "object") {
                d.each(function (t) {
                    if (t.id == n) {
                        r = t
                    }
                })
            }
            a.links.push({source: M, target: r, type: t});
            U(a)
        }

        function W(e) {
            if ((typeof e === "undefined" ? "undefined" : _typeof(e)) !== "object") {
                d.each(function (t) {
                    if (t.id == e) {
                        e = t
                    }
                })
            }
            a.nodes.splice(e.index, 1);
            a.links = a.links.filter(function (t) {
                return t.source !== e && t.target !== e
            });
            L.filter(function (t) {
                return t.source === e || t.target === e
            }).remove();
            if (tt.length !== 0) {
                tt.filter(function (t) {
                    return t.source === e || t.target === e
                }).remove()
            }
            var t = d.filter(function (t) {
                return t === e
            });
            t.selectAll("circle").style("stroke-width", 0).transition().duration(300).ease(d3.easeLinear).attr("r", 0);
            setTimeout(function () {
                t.remove()
            }, 1100)
        }

        function J(t) {
            a.links.splice(t.index, 1);
            var e = t.source.id, n = t.target.id;
            L.each(function (t) {
                if (t.source.id === e && t.target.id === n) {
                    d3.select(this).remove()
                }
            })
        }

        function V(n, t, e) {
            t.forEach(function (e) {
                a.nodes.forEach(function (t) {
                    if (t.id === e.source) {
                        e.source = t
                    } else if (t.id === e.target) {
                        e.target = t
                    }
                });
                var t = n.getData();
                t.nodes.forEach(function (t) {
                    if (t.id === e.source) {
                        e.source = t
                    } else if (t.id === e.target) {
                        e.target = t
                    }
                })
            });
            if (e === true) {
                s.force("link", d3.forceLink().id(function (t) {
                    return t.id
                }).strength(.005)).force("charge", d3.forceManyBody().strength(y.graph.repulsion)).force("link").links(t)
                .distance(function(d){
                	return d.color*25;
                })
            }
            f(t, $, "0")
        }

        return {
            getData: P,
            startLayout: _,
            stopLayout: Z,
            changeLayoutMode: B,
            changeAnalyseMode: G,
            fillText: g,
            addNode: H,
            removeNode: W,
            addLink: R,
            removeLink: J,
            connectGraph: V
        }
    }

    var q = null, K = null, $ = null, tt = [];
    var et = Object();

    function M(t) {
        q = d3.select("#" + (typeof t !== "undefined" ? t : "body"));
        q.on("click", function () {
            d3.selectAll(".menu").remove()
        });
        K = q.append("g").attr("id", "container");
        $ = K.append("g").attr("id", "connect_layout");
        et = document.getElementById(t).getBoundingClientRect();
        return {getDemoData: e, drawNetworkGraph: n}
    }

    window.NetworkGraph = M;
    var nt = "data:image/svg+xml;base64," + "PD94bWwgdmVyc2lvbj0nMS4wJyBlbmNvZGluZz0naXNvLTg4NTktMSc/Pgo8IURPQ1RZUEUgc3ZnIFBVQkx" + "JQyAnLS8vVzNDLy9EVEQgU1ZHIDEuMS8vRU4nICdodHRwOi8vd3d3LnczLm9yZy9HcmFwaGljcy9TVkcvMS" + "4xL0RURC9zdmcxMS5kdGQnPgo8c3ZnIHZlcnNpb249IjEuMSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnL" + "zIwMDAvc3ZnIiB2aWV3Qm94PSIwIDAgMjI2Ljk5IDIyNi45OSIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cu" + "dzMub3JnLzE5OTkveGxpbmsiIGVuYWJsZS1iYWNrZ3JvdW5kPSJuZXcgMCAwIDIyNi45OSAyMjYuOTkiPgo" + "gIDxnPgogICAgPHBhdGggZD0ibTIyNC4yNDgsNjcuMjUyYy0yLjkwOC01LjQzMi03Ljc1Ny05LjQwNS0xMy" + "42NTQtMTEuMTg5LTUuODk3LTEuNzg1LTEyLjEzNi0xLjE2NS0xNy41NjcsMS43NDMtNS40MzEsMi45MDktO" + "S40MDUsNy43NTgtMTEuMTg5LDEzLjY1NS0xLjE4OSwzLjkyOC0xLjI4Niw4LjAwNC0wLjM3NiwxMS44OTVs" + "LTI0LjkwNSwxMy4zMzZjLTcuNTc1LTEwLjYzNS0xOS4zODMtMTguMDQ0LTMyLjk1LTE5Ljc1MXYtMjEuMjU" + "zYzkuODE4LTIuNjQ4IDE3LjA2NS0xMS42MjYgMTcuMDY1LTIyLjI2OSAwLTEyLjcxOC0xMC4zNDctMjMuMD" + "Y1LTIzLjA2NS0yMy4wNjVzLTIzLjA2NiwxMC4zNDctMjMuMDY2LDIzLjA2NmMwLDEwLjY0MyA3LjI0OCwxO" + "S42MjEgMTcuMDY1LDIyLjI2OXYyMS4yNTFjLTE3LjMyOCwyLjE4MS0zMS43OTUsMTMuNjY0LTM4LjIyMywy" + "OS4yODRsLTI3LjI1Ny03LjM0NGMwLjAwNi0zLjk5Ny0xLjAxMi03Ljk0NS0zLjA2LTExLjUwMi0zLjA3My0" + "1LjM0LTguMDQyLTkuMTY0LTEzLjk5LTEwLjc2Ny0xMi4yODQtMy4zMS0yNC45NjMsMy45OTEtMjguMjcyLD" + "E2LjI3MS0xLjYwMyw1Ljk0OS0wLjc5MywxMi4xNjYgMi4yOCwxNy41MDVzOC4wNDIsOS4xNjQgMTMuOTksM" + "TAuNzY3YzEuOTk3LDAuNTM4IDQuMDIzLDAuODA0IDYuMDM4LDAuODA0IDMuOTg1LDAgNy45Mi0xLjA0MyAx" + "MS40NjgtMy4wODQgMy41NTctMi4wNDcgNi40MjEtNC45NDggOC40MjQtOC40MDdsMjcuMjU4LDcuMzQ1Yy0" + "wLjI5NCwyLjE0MS0wLjQ1OSw0LjMyMi0wLjQ1OSw2LjU0MyAwLDExLjAyMSAzLjc1OSwyMS4xNzUgMTAuMD" + "Q5LDI5LjI3bC0xOS45ODUsMTkuOTg1Yy04LjgxNC01LjA2NC0yMC4yNzQtMy44NTktMjcuNzk4LDMuNjYzL" + "TguOTkzLDguOTkzLTguOTkzLDIzLjYyNiAwLDMyLjYyIDQuNDk3LDQuNDk3IDEwLjQwMyw2Ljc0NSAxNi4z" + "MSw2Ljc0NSA1LjkwNiwwIDExLjgxMy0yLjI0OCAxNi4zMS02Ljc0NSA3LjUyMy03LjUyNCA4LjcyOC0xOC4" + "5ODQgMy42NjMtMjcuNzk3bDE5Ljk4NS0xOS45ODVjOC4wOTUsNi4yOSAxOC4yNDgsMTAuMDQ5IDI5LjI3LD" + "EwLjA0OXMyMS4xNzUtMy43NTkgMjkuMjctMTAuMDQ5bDE5Ljk4NSwxOS45ODVjLTUuMDY0LDguODE0LTMuO" + "DYsMjAuMjc0IDMuNjYzLDI3Ljc5NyA0LjQ5Nyw0LjQ5NyAxMC40MDMsNi43NDUgMTYuMzEsNi43NDUgNS45" + "MDcsMCAxMS44MTMtMi4yNDkgMTYuMzEtNi43NDUgOC45OTMtOC45OTMgOC45OTMtMjMuNjI2IDAtMzIuNjI" + "tNy41MjMtNy41MjMtMTguOTg0LTguNzI3LTI3Ljc5Ny0zLjY2M2wtMTkuOTg1LTE5Ljk4NWM2LjI5LTguMD" + "k1IDEwLjA0OS0xOC4yNDggMTAuMDQ5LTI5LjI3IDAtNi4wMjEtMS4xMy0xMS43ODEtMy4xNzEtMTcuMDkzb" + "DI0Ljg4Ny0xMy4zMjZjMi43MzQsMi45MTUgNi4xOCw1LjA5MyAxMC4xMDksNi4yODIgMi4yMDgsMC42Njgg" + "NC40NjUsMC45OTkgNi43MDksMC45OTkgMy43NDgsMCA3LjQ2MS0wLjkyNCAxMC44NTgtMi43NDMgNS40MzI" + "tMi45MDggOS40MDUtNy43NTcgMTEuMTg5LTEzLjY1NHMxLjE2My0xMi4xMzctMS43NDUtMTcuNTY4em0tMT" + "UuMTExLDIwLjY0MmMtMi42MDUsMS4zOTYtNS41OTgsMS42OTMtOC40MjcsMC44MzYtMi44MjktMC44NTUtN" + "S4xNTUtMi43NjItNi41NS01LjM2OC0xLjM5Ni0yLjYwNS0xLjY5Mi01LjU5OS0wLjgzNi04LjQyOCAwLjg1" + "NS0yLjgyOSAyLjc2Mi01LjE1NSA1LjM2OC02LjU1IDEuNjMtMC44NzMgMy40MTEtMS4zMTYgNS4yMDktMS4" + "zMTYgMS4wNzYsMCAyLjE1OSwwLjE1OSAzLjIxOCwwLjQ3OSAyLjgyOSwwLjg1NSA1LjE1NSwyLjc2MiA2Lj" + "U1LDUuMzY4IDEuMzk2LDIuNjA1IDEuNjkyLDUuNTk4IDAuODM2LDguNDI3LTAuODU1LDIuODMxLTIuNzYyL" + "DUuMTU3LTUuMzY4LDYuNTUyem0tNTUuNzI3LDM2LjQ2YzAsMTkuNzQyLTE2LjA2MiwzNS44MDQtMzUuODA0" + "LDM1LjgwNHMtMzUuODA0LTE2LjA2Mi0zNS44MDQtMzUuODA0IDE2LjA2Mi0zNS44MDQgMzUuODA0LTM1Ljg" + "wNCAzNS44MDQsMTYuMDYyIDM1LjgwNCwzNS44MDR6bS00Ni44NjktOTAuOTM0YzAtNi4xMDIgNC45NjQtMT" + "EuMDY1IDExLjA2NS0xMS4wNjVzMTEuMDY1LDQuOTY0IDExLjA2NSwxMS4wNjVjMCw2LjEwMS00Ljk2NCwxM" + "S4wNjUtMTEuMDY1LDExLjA2NXMtMTEuMDY1LTQuOTY0LTExLjA2NS0xMS4wNjV6bS04Ni4zNDUsNzYuMTQ3" + "Yy0yLjg1NC0wLjc2OS01LjIzNy0yLjYwNC02LjcxMS01LjE2NS0xLjQ3NS0yLjU2Mi0xLjg2My01LjU0NC0" + "xLjA5NC04LjM5OCAxLjU4Ny01Ljg5MiA3LjY3Mi05LjM5MyAxMy41NjMtNy44MDYgMi44NTQsMC43NjkgNS" + "4yMzcsMi42MDMgNi43MTIsNS4xNjUgMS40NzQsMi41NjIgMS44NjIsNS41NDQgMS4wOTMsOC4zOTgtMS41O" + "DcsNS44OTItNy42Nyw5LjM5My0xMy41NjMsNy44MDZ6bTM2LjAwNyw5MS44MzljLTQuMzEzLDQuMzE0LTEx" + "LjMzNCw0LjMxNC0xNS42NDksMC00LjMxNC00LjMxNC00LjMxNC0xMS4zMzQgMC0xNS42NDkgMi4xNTctMi4" + "xNTcgNC45OTEtMy4yMzUgNy44MjUtMy4yMzUgMi44MzMsMCA1LjY2NywxLjA3OSA3LjgyNCwzLjIzNSA0Lj" + "MxNCw0LjMxNSA0LjMxNCwxMS4zMzUgMCwxNS42NDl6bTEzOC40NTUtMTUuNjQ5YzQuMzE0LDQuMzE0IDQuM" + "zE0LDExLjMzNCAwLDE1LjY0OS00LjMxMyw0LjMxNC0xMS4zMzQsNC4zMTQtMTUuNjQ5LDAtNC4zMTQtNC4z" + "MTQtNC4zMTQtMTEuMzM0IDAtMTUuNjQ5IDIuMTU3LTIuMTU3IDQuOTkxLTMuMjM1IDcuODI1LTMuMjM1IDI" + "uODMzLDAgNS42NjcsMS4wNzggNy44MjQsMy4yMzV6Ii8+CiAgPC9nPgo8L3N2Zz4K";
    var Mt = "data:image/svg+xml;base64," + "Cjxzdmcgdmlld0JveD0iMCAwIDE3IDE2IiB2ZXJzaW9uPSIxLjEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9" + "yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIGNsYXNzPS" + "JzaS1nbHlwaCBzaS1nbHlwaC1saW5rLTEiPjx0aXRsZT44MjA8L3RpdGxlPjxkZWZzPjwvZGVmcz48ZyBzd" + "HJva2U9Im5vbmUiIHN0cm9rZS13aWR0aD0iMSIgZmlsbD0ibm9uZSIgZmlsbC1ydWxlPSJldmVub2RkIj48" + "ZyB0cmFuc2Zvcm09InRyYW5zbGF0ZSgxLjAwMDAwMCwgNS4wMDAwMDApIiBmaWxsPSIjNDM0MzQzIj48cmV" + "jdCB4PSI0IiB5PSIyIiB3aWR0aD0iNi45NzkiIGhlaWdodD0iMC45NTkiIGNsYXNzPSJzaS1nbHlwaC1maW" + "xsIj48L3JlY3Q+PGc+PHBhdGggZD0iTTEzLjYwNCwwIEw5LjM4NSwwIEM4LjY0MiwwIDguMDM1LDAuNTkxI" + "DguMDM1LDEuMzE4IEw4LjAzNSwzLjY2MiBDOC4wMzUsNC4zODggOC42NDEsNC45NzkgOS4zODUsNC45Nzkg" + "TDEzLjYwNCw0Ljk3OSBDMTQuMzQ4LDQuOTc5IDE0Ljk1Miw0LjM4NyAxNC45NTIsMy42NjIgTDE0Ljk1Miw" + "xLjMxOCBDMTQuOTUxLDAuNTkxIDE0LjM0OCwwIDEzLjYwNCwwIEwxMy42MDQsMCBaIE0xNC4wMjQsMy42Nz" + "kgQzE0LjAyNCwzLjg3MSAxMy44NTMsNC4wMjcgMTMuNjQzLDQuMDI3IEw5LjMyNSw0LjAyNyBDOS4xMTUsN" + "C4wMjcgOC45NDUsMy44NzIgOC45NDUsMy42NzkgTDguOTQ1LDEuMzAyIEM4Ljk0NSwxLjExIDkuMTE1LDAu" + "OTUzIDkuMzI1LDAuOTUzIEwxMy42NDMsMC45NTMgQzEzLjg1MywwLjk1MyAxNC4wMjQsMS4xMDkgMTQuMDI" + "0LDEuMzAyIEwxNC4wMjQsMy42NzkgTDE0LjAyNCwzLjY3OSBaIiBjbGFzcz0ic2ktZ2x5cGgtZmlsbCI+PC" + "9wYXRoPjxwYXRoIGQ9Ik01LjYyMSwwIEwxLjM3NywwIEMwLjYyOCwwIDAuMDIsMC41OTEgMC4wMiwxLjMxO" + "CBMMC4wMiwzLjY2MiBDMC4wMiw0LjM4OCAwLjYyOCw0Ljk3OSAxLjM3Nyw0Ljk3OSBMNS42MjEsNC45Nzkg" + "QzYuMzY5LDQuOTc5IDYuOTc3LDQuMzg3IDYuOTc3LDMuNjYyIEw2Ljk3NywxLjMxOCBDNi45NzgsMC41OTE" + "gNi4zNjksMCA1LjYyMSwwIEw1LjYyMSwwIFogTTYuMDQ5LDMuNjYyIEM2LjA0OSwzLjg1MSA1Ljg3Nyw0Lj" + "AwNSA1LjY2OCw0LjAwNSBMMS4zNSw0LjAwNSBDMS4xNDEsNC4wMDUgMC45NjksMy44NTIgMC45NjksMy42N" + "jIgTDAuOTY5LDEuMzE4IEMwLjk2OSwxLjEyOSAxLjE0MSwwLjk3NCAxLjM1LDAuOTc0IEw1LjY2OCwwLjk3" + "NCBDNS44NzcsMC45NzQgNi4wNDksMS4xMjggNi4wNDksMS4zMTggTDYuMDQ5LDMuNjYyIEw2LjA0OSwzLjY" + "2MiBaIiBjbGFzcz0ic2ktZ2x5cGgtZmlsbCI+PC9wYXRoPjwvZz48L2c+PC9nPjwvc3ZnPg==";
    var rt = "data:image/svg+xml;base64," + "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0idXRmLTgiPz4KPCEtLSBHZW5lcmF0ZWQgYnkgSWNvTW9" + "vbi5pbyAtLT4KPCFET0NUWVBFIHN2ZyBQVUJMSUMgIi0vL1czQy8vRFREIFNWRyAxLjEvL0VOIiAiaHR0cD" + "ovL3d3dy53My5vcmcvR3JhcGhpY3MvU1ZHLzEuMS9EVEQvc3ZnMTEuZHRkIj4KPHN2ZyB2ZXJzaW9uPSIxL" + "jEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cu" + "dzMub3JnLzE5OTkveGxpbmsiIHdpZHRoPSI1MTIiIGhlaWdodD0iNTEyIiB2aWV3Qm94PSIwIDAgNTEyIDU" + "xMiI+CjxnPgo8L2c+Cgk8cGF0aCBkPSJNNDAwIDY0aC0yODhjLTI2LjUxIDAtNDggMjEuNDktNDggNDh2MT" + "ZoMzg0di0xNmMwLTI2LjUxLTIxLjQ5LTQ4LTQ4LTQ4ek0zMTYuMTYgMzJsNy4wNTggNTAuNWgtMTM0LjQzN" + "mw3LjA1Ny01MC41aDEyMC4zMjF6TTMyMCAwaC0xMjhjLTEzLjIgMC0yNS40OTUgMTAuNjk2LTI3LjMyMSAy" + "My43NjlsLTkuMzU3IDY2Ljk2MmMtMS44MjcgMTMuMDczIDcuNDc4IDIzLjc2OSAyMC42NzggMjMuNzY5aDE" + "2MGMxMy4yIDAgMjIuNTA1LTEwLjY5NiAyMC42NzktMjMuNzY5bC05LjM1Ny02Ni45NjJjLTEuODI3LTEzLj" + "A3My0xNC4xMjItMjMuNzY5LTI3LjMyMi0yMy43Njl2MHpNNDA4IDE2MGgtMzA0Yy0xNy42IDAtMzAuNjk2I" + "DE0LjM0MS0yOS4xMDMgMzEuODY5bDI2LjIwNiAyODguMjYzYzEuNTkzIDE3LjUyNyAxNy4yOTcgMzEuODY4" + "IDM0Ljg5NyAzMS44NjhoMjQwYzE3LjYgMCAzMy4zMDQtMTQuMzQxIDM0Ljg5Ny0zMS44NjhsMjYuMjA1LTI" + "4OC4yNjNjMS41OTQtMTcuNTI4LTExLjUwMi0zMS44NjktMjkuMTAyLTMxLjg2OXpNMTkyIDQ0OGgtNDhsLT" + "E2LTIyNGg2NHYyMjR6TTI4OCA0NDhoLTY0di0yMjRoNjR2MjI0ek0zNjggNDQ4aC00OHYtMjI0aDY0bC0xN" + "iAyMjR6IiBmaWxsPSIjMDAwMDAwIiAvPgo8L3N2Zz4K"
})();
