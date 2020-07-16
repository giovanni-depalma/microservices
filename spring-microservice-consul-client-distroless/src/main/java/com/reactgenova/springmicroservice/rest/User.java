package com.reactgenova.springmicroservice.rest;

import java.util.Objects;


public class User {
        private String userName;
        private String firstName;
        private String lastName;
        private String description;

        public User() {
        }

        public User(String userName, String firstName, String lastName, String description) {
                this.userName = userName;
                this.firstName = firstName;
                this.lastName = lastName;
                this.description = description;
        }

        public String getUserName() {
                return this.userName;
        }

        public void setUserName(String userName) {
                this.userName = userName;
        }

        public String getFirstName() {
                return this.firstName;
        }

        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        public String getLastName() {
                return this.lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        public String getDescription() {
                return this.description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public User userName(String userName) {
                this.userName = userName;
                return this;
        }

        public User firstName(String firstName) {
                this.firstName = firstName;
                return this;
        }

        public User lastName(String lastName) {
                this.lastName = lastName;
                return this;
        }

        public User description(String description) {
                this.description = description;
                return this;
        }

        @Override
        public boolean equals(Object o) {
                if (o == this)
                        return true;
                if (!(o instanceof User)) {
                        return false;
                }
                User user = (User) o;
                return Objects.equals(userName, user.userName) && Objects.equals(firstName, user.firstName)
                                && Objects.equals(lastName, user.lastName)
                                && Objects.equals(description, user.description);
        }

        @Override
        public int hashCode() {
                return Objects.hash(userName, firstName, lastName, description);
        }

        @Override
        public String toString() {
                return "{" + " userName='" + getUserName() + "'" + ", firstName='" + getFirstName() + "'"
                                + ", lastName='" + getLastName() + "'" + ", description='" + getDescription() + "'"
                                + "}";
        }

}