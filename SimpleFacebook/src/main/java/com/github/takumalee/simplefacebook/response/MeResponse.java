package com.github.takumalee.simplefacebook.response;

import java.util.List;

/**
 * Created by Nijugon on 2015/6/6.
 */
public class MeResponse {


    private String birthday;
    private List<String> interested_in;
    private String updated_time;
    private List<EducationEntity> education;
    private String gender;
    private Significant_otherEntity significant_other;
    private int timezone;
    private List<WorkEntity> work;
    private String link;
    private boolean verified;
    private String bio;
    private String last_name;
    private String locale;
    private String relationship_status;
    private String name;
    private LocationEntity location;
    private String id;
    private String first_name;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public List<String> getInterested_in() {
        return interested_in;
    }

    public void setInterested_in(List<String> interested_in) {
        this.interested_in = interested_in;
    }

    public String getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(String updated_time) {
        this.updated_time = updated_time;
    }

    public List<EducationEntity> getEducation() {
        return education;
    }

    public void setEducation(List<EducationEntity> education) {
        this.education = education;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Significant_otherEntity getSignificant_other() {
        return significant_other;
    }

    public void setSignificant_other(Significant_otherEntity significant_other) {
        this.significant_other = significant_other;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public List<WorkEntity> getWork() {
        return work;
    }

    public void setWork(List<WorkEntity> work) {
        this.work = work;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getRelationship_status() {
        return relationship_status;
    }

    public void setRelationship_status(String relationship_status) {
        this.relationship_status = relationship_status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocationEntity getLocation() {
        return location;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public class EducationEntity {
        /**
         * school : {"name":"樹林高中","id":"188465811203817"}
         * type : High School
         */
        private SchoolEntity school;
        private String type;

        public SchoolEntity getSchool() {
            return school;
        }

        public void setSchool(SchoolEntity school) {
            this.school = school;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public class SchoolEntity {
            /**
             * name : 樹林高中
             * id : 188465811203817
             */
            private String name;
            private String id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }

    public class Significant_otherEntity {
        /**
         * name : Na Mi
         * id : 897230850300523
         */
        private String name;
        private String id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public class WorkEntity {
        /**
         * employer : {"name":"胡桃宇宙","id":"1605031976389971"}
         * location : {"name":"Taipei, Taiwan","id":"110765362279102"}
         * position : {"name":"Android Developer","id":"146255685390317"}
         * start_date : 2014-07-22
         */
        private EmployerEntity employer;
        private LocationEntity location;
        private PositionEntity position;
        private String start_date;

        public EmployerEntity getEmployer() {
            return employer;
        }

        public void setEmployer(EmployerEntity employer) {
            this.employer = employer;
        }

        public LocationEntity getLocation() {
            return location;
        }

        public void setLocation(LocationEntity location) {
            this.location = location;
        }

        public PositionEntity getPosition() {
            return position;
        }

        public void setPosition(PositionEntity position) {
            this.position = position;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public class EmployerEntity {
            /**
             * name : 胡桃宇宙
             * id : 1605031976389971
             */
            private String name;
            private String id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

        public class LocationEntity {
            /**
             * name : Taipei, Taiwan
             * id : 110765362279102
             */
            private String name;
            private String id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

        public class PositionEntity {
            /**
             * name : Android Developer
             * id : 146255685390317
             */
            private String name;
            private String id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }

    public class LocationEntity {
        /**
         * name : Taipei, Taiwan
         * id : 110765362279102
         */
        private String name;
        private String id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
