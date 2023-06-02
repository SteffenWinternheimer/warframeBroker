import java.util.List;

public class Data {
    private Payload payload;

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public static class Payload {
        private List<Order> orders;

        public List<Order> getOrders() {
            return orders;
        }

        public void setOrders(List<Order> orders) {
            this.orders = orders;
        }

        public static class Order {
            private int platinum;
            private int quantity;
            private String order_type;
            private Data.Payload.Order.User user;
            private String platform;
            private String creation_date;
            private String last_update;
            private boolean visible;
            private String id;
            private String region;

            public int getPlatinum() {
                return platinum;
            }

            public void setPlatinum(int platinum) {
                this.platinum = platinum;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public String getOrder_type() {
                return order_type;
            }

            public void setOrder_type(String order_type) {
                this.order_type = order_type;
            }

            public Data.Payload.Order.User getUser() {
                return user;
            }

            public void setUser(Data.Payload.Order.User user) {
                this.user = user;
            }

            public String getPlatform() {
                return platform;
            }

            public void setPlatform(String platform) {
                this.platform = platform;
            }

            public String getCreation_date() {
                return creation_date;
            }

            public void setCreation_date(String creation_date) {
                this.creation_date = creation_date;
            }

            public String getLast_update() {
                return last_update;
            }

            public void setLast_update(String last_update) {
                this.last_update = last_update;
            }

            public boolean isVisible() {
                return visible;
            }

            public void setVisible(boolean visible) {
                this.visible = visible;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public static class User {
                private int reputation;
                private String locale;
                private String avatar;
                private String last_seen;
                private String ingame_name;
                private String id;
                private String region;
                private String status;

                public int getReputation() {
                    return reputation;
                }

                public void setReputation(int reputation) {
                    this.reputation = reputation;
                }

                public String getLocale() {
                    return locale;
                }

                public void setLocale(String locale) {
                    this.locale = locale;
                }

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public String getLast_seen() {
                    return last_seen;
                }

                public void setLast_seen(String last_seen) {
                    this.last_seen = last_seen;
                }

                public String getIngame_name() {
                    return ingame_name;
                }

                public void setIngame_name(String ingame_name) {
                    this.ingame_name = ingame_name;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getRegion() {
                    return region;
                }

                public void setRegion(String region) {
                    this.region = region;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }
            }
        }
    }
}