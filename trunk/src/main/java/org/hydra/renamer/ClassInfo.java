package org.hydra.renamer;

import java.util.ArrayList;
import java.util.List;

public class ClassInfo {
    // internalName like java/lang/String
    private String className;
    // direct superClass
    private ClassInfo superClass;
    // all interfaces
    private List<ClassInfo> interfaces;
    // direct children
    private List<ClassInfo> children;

    private List<Pair> methods;
    private List<Pair> fields;

    private boolean isInterface;

    public ClassInfo(String name, boolean isInterface) {
        this.className = name;
        this.isInterface = isInterface;
        this.interfaces = new ArrayList<ClassInfo>(0);
        this.children = new ArrayList<ClassInfo>(0);
        this.methods = new ArrayList<Pair>(0);
        this.fields = new ArrayList<Pair>(0);
    }

    public void setSuperClass(ClassInfo superClass) {
        this.superClass = superClass;
    }

    public String getClassName() {
        return className;
    }

    public ClassInfo getSuperClass() {
        return superClass;
    }

    public List<ClassInfo> getInterfaces() {
        return interfaces;
    }

    public List<ClassInfo> getChildren() {
        return children;
    }

    public List<Pair> getMethods() {
        return methods;
    }

    public List<Pair> getFields() {
        return fields;
    }

    public String toString() {
        StringBuilder buff = new StringBuilder();
        buff.append("ClassInfo{").append(isInterface ? "interface" : "class").append(":").append(this.className);
        if (this.superClass != null) {
            buff.append(",super:").append(superClass.getClassName());
        }
        if (this.interfaces.size() > 0) {
            if (isInterface) {
                buff.append(",superInterfaces[");
            } else {
                buff.append(",interfaces[");
            }
            for (ClassInfo info : this.interfaces) {
                buff.append(info.getClassName()).append(",");
            }
            buff.append("]");
        }
        if (this.fields.size() > 0) {
            buff.append(",fields:[");
            for (Pair p : this.fields) {
                buff.append(p.getLeft()).append("/").append(p.getRight()).append(",");
            }
            buff.append("]");
        }
        if (this.methods.size() > 0) {
            buff.append(",methods:[");
            for (Pair p : this.methods) {
                buff.append(p.getLeft()).append("/").append(p.getRight()).append(",");
            }
            buff.append("]");
        }
        buff.append("}");
        // return
        // String.format("ClassInfo [%s],superClass [%s],interfaces [%s],children [%s]",
        // className,
        // superClass == null ? null : superClass.getClassName(), interfaces,
        // children);
        return buff.toString();
    }
}
