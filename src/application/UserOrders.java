package application;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class UserOrders {
    public static class UserOrdersResponse {
        private Payload payload;
        private Include include;

        public Payload getPayload() {
            return payload;
        }

        public Include getInclude() {
            return include;
        }
    }

    public static class Payload {
        @SerializedName("sell_orders")
        private List<Order> sellOrders;
        @SerializedName("buy_orders")
        private List<Order> buyOrders;

        public List<Order> getSellOrders() {
            return sellOrders;
        }

        public List<Order> getBuyOrders() {
            return buyOrders;
        }
    }

    public static class Order {
        @SerializedName("order_type")
        private String orderType;
        private boolean visible;
        private String id;
        private String subtype;
        @SerializedName("creation_date")
        private String creationDate;
        private int platinum;
        @SerializedName("last_update")
        private String lastUpdate;
        private String region;
        private int quantity;
        private String platform;
        private Item item;

        public String getOrderType() {
            return orderType;
        }

        public boolean isVisible() {
            return visible;
        }

        public String getId() {
            return id;
        }

        public String getSubtype() {
            return subtype;
        }

        public LocalDateTime getCreationDate() {
            return parseDateTime(creationDate);
        }

        public int getPlatinum() {
            return platinum;
        }

        public LocalDateTime getLastUpdate() {
            return parseDateTime(lastUpdate);
        }

        public String getRegion() {
            return region;
        }

        public int getQuantity() {
            return quantity;
        }

        public String getPlatform() {
            return platform;
        }

        public Item getItem() {
            return item;
        }

        private LocalDateTime parseDateTime(String dateTime) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            return LocalDateTime.parse(dateTime, formatter);
        }
    }

    public static class Item {
        private String[] tags;
        @SerializedName("icon_format")
        private String iconFormat;
        private String id;
        @SerializedName("url_name")
        private String urlName;
        @SerializedName("sub_icon")
        private String subIcon;
        private String thumb;
        private String icon;
        private String[] subtypes;
        private Localization en;
        private Localization ru;
        private Localization ko;
        // weitere Localizations ...

        public String[] getTags() {
            return tags;
        }

        public String getIconFormat() {
            return iconFormat;
        }

        public String getId() {
            return id;
        }

        public String getUrlName() {
            return urlName;
        }

        public String getSubIcon() {
            return subIcon;
        }

        public String getThumb() {
            return thumb;
        }

        public String getIcon() {
            return icon;
        }

        public String[] getSubtypes() {
            return subtypes;
        }

        public Localization getEn() {
            return en;
        }

        public Localization getRu() {
            return ru;
        }

        public Localization getKo() {
            return ko;
        }

        // weitere Getter für Localizations ...
    }

    public static class Localization {
        @SerializedName("item_name")
        private String itemName;

        public String getItemName() {
            return itemName;
        }
    }

    public static class Include {
        private Profile profile;

        public Profile getProfile() {
            return profile;
        }
    }
}
