package com.uf.rest.bean.request;

public class RegistUserRequest {
		private String name;
		private Integer p;
		private String password;
		private Integer type;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Integer getP() {
			return p;
		}
		public void setP(Integer p) {
			this.p = p;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public Integer getType() {
			return type;
		}
		public void setType(Integer type) {
			this.type = type;
		}
		
}
