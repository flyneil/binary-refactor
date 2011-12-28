package org.hydra.util;

import java.util.ArrayList;
import java.util.List;

import org.hydra.util.Lists.AccFunc;

public class MethodSignature {
	public MethodSignature(String name2) {
		this.name = name2;
	}

	private String name;
	private ClassSignature returnType;
	private int flags;
	private List<ClassSignature> params = new ArrayList<ClassSignature>();
	private List<ClassSignature> exceptions = new ArrayList<ClassSignature>();

	public void setFlags(int access) {
		this.flags = access;
	}

	public void setReturnType(ClassSignature classSignature) {
		this.returnType = classSignature;
	}

	public void addParam(ClassSignature classSignature) {
		this.params.add(classSignature);
	}

	public void addException(ClassSignature classSignature) {
		this.exceptions.add(classSignature);
	}

	@Override
	public String toString() {
		if (this.ignore) {
			return "MethodSignature [" + name + "]\n";
		}
		return "MethodSignature [name=" + name + ", returnType=" + returnType + ", flags=" + flags + ", params="
		        + params + ", exceptions=" + exceptions + "]\n";
	}

	private boolean ignore = false;
	private String originDesc;

	public boolean isIgnore() {
		return this.ignore;
	}

	public void setIgnore(boolean b) {
		this.ignore = b;
	}

	/**
	 * 计算粗略的特征信息，不涉及类型，只关心数量，不递归
	 */
	public String getLevel0Sig() {
		StringBuilder sb = new StringBuilder();
		sb.append(Types.qualify(this.returnType.getName())).append(" ");
		if ("<init>".equals(this.name) || "<clinit>".equals(this.name)) {
			sb.append(this.name);
		} else {
			sb.append("__");
		}
		sb.append("(").append(join(this.params)).append(")");
		sb.append("").append(join(this.exceptions));
		return sb.toString();
	}

	private String join(List<ClassSignature> p) {
		return Lists.acc(p, "", new AccFunc<ClassSignature, String>() {

			public String apply(ClassSignature in, String out) {
				return out + Types.qualify(in.getName()) + ",";
			}
		});
	}

	public String getName() {
		return name;
	}

	public ClassSignature getReturnType() {
		return returnType;
	}

	public List<ClassSignature> getParams() {
		return params;
	}

	public List<ClassSignature> getExceptions() {
		return exceptions;
	}

	public void setOriginDesc(String desc) {
		this.originDesc = desc;
	}

	public String getOriginDesc() {
		return this.originDesc;
	}
}
