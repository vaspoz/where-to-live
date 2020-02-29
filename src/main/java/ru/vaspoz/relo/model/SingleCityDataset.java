package ru.vaspoz.relo.model;

import javax.persistence.*;

@Entity
@Table(name = "CITY_RATES_FULL")
public class SingleCityDataset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "CITY")
    private String city;

    @Column(name = "VALUE_01")
    private Double value_01;
    @Column(name = "VALUE_02")
    private Double value_02;
    @Column(name = "VALUE_03")
    private Double value_03;
    @Column(name = "VALUE_04")
    private Double value_04;
    @Column(name = "VALUE_05")
    private Double value_05;
    @Column(name = "VALUE_06")
    private Double value_06;
    @Column(name = "VALUE_07")
    private Double value_07;
    @Column(name = "VALUE_08")
    private Double value_08;
    @Column(name = "VALUE_09")
    private Double value_09;
    @Column(name = "VALUE_10")
    private Double value_10;
    @Column(name = "VALUE_11")
    private Double value_11;
    @Column(name = "VALUE_12")
    private Double value_12;
    @Column(name = "VALUE_13")
    private Double value_13;
    @Column(name = "VALUE_14")
    private Double value_14;
    @Column(name = "VALUE_15")
    private Double value_15;
    @Column(name = "VALUE_16")
    private Double value_16;
    @Column(name = "VALUE_17")
    private Double value_17;
    @Column(name = "VALUE_18")
    private Double value_18;
    @Column(name = "VALUE_19")
    private Double value_19;
    @Column(name = "VALUE_20")
    private Double value_20;
    @Column(name = "VALUE_21")
    private Double value_21;
    @Column(name = "VALUE_22")
    private Double value_22;
    @Column(name = "VALUE_23")
    private Double value_23;
    @Column(name = "VALUE_24")
    private Double value_24;
    @Column(name = "VALUE_25")
    private Double value_25;
    @Column(name = "VALUE_26")
    private Double value_26;
    @Column(name = "VALUE_27")
    private Double value_27;
    @Column(name = "VALUE_28")
    private Double value_28;
    @Column(name = "VALUE_29")
    private Double value_29;
    @Column(name = "VALUE_30")
    private Double value_30;
    @Column(name = "VALUE_31")
    private Double value_31;
    @Column(name = "VALUE_32")
    private Double value_32;
    @Column(name = "VALUE_33")
    private Double value_33;
    @Column(name = "VALUE_34")
    private Double value_34;
    @Column(name = "VALUE_35")
    private Double value_35;
    @Column(name = "VALUE_36")
    private Double value_36;
    @Column(name = "VALUE_37")
    private Double value_37;
    @Column(name = "VALUE_38")
    private Double value_38;
    @Column(name = "VALUE_39")
    private Double value_39;
    @Column(name = "VALUE_40")
    private Double value_40;
    @Column(name = "VALUE_41")
    private Double value_41;
    @Column(name = "VALUE_42")
    private Double value_42;
    @Column(name = "VALUE_43")
    private Double value_43;
    @Column(name = "VALUE_44")
    private Double value_44;
    @Column(name = "VALUE_45")
    private Double value_45;
    @Column(name = "VALUE_46")
    private Double value_46;
    @Column(name = "VALUE_47")
    private Double value_47;
    @Column(name = "VALUE_48")
    private Double value_48;
    @Column(name = "VALUE_49")
    private Double value_49;
    @Column(name = "VALUE_50")
    private Double value_50;
    @Column(name = "VALUE_51")
    private Double value_51;
    @Column(name = "VALUE_52")
    private Double value_52;
    @Column(name = "VALUE_53")
    private Double value_53;
    @Column(name = "VALUE_54")
    private Double value_54;
    @Column(name = "VALUE_55")
    private Double value_55;

    public SingleCityDataset() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getValue_01() {
        return value_01;
    }

    public void setValue_01(Double value_01) {
        this.value_01 = value_01;
    }

    public Double getValue_02() {
        return value_02;
    }

    public void setValue_02(Double value_02) {
        this.value_02 = value_02;
    }

    public Double getValue_03() {
        return value_03;
    }

    public void setValue_03(Double value_03) {
        this.value_03 = value_03;
    }

    public Double getValue_04() {
        return value_04;
    }

    public void setValue_04(Double value_04) {
        this.value_04 = value_04;
    }

    public Double getValue_05() {
        return value_05;
    }

    public void setValue_05(Double value_05) {
        this.value_05 = value_05;
    }

    public Double getValue_06() {
        return value_06;
    }

    public void setValue_06(Double value_06) {
        this.value_06 = value_06;
    }

    public Double getValue_07() {
        return value_07;
    }

    public void setValue_07(Double value_07) {
        this.value_07 = value_07;
    }

    public Double getValue_08() {
        return value_08;
    }

    public void setValue_08(Double value_08) {
        this.value_08 = value_08;
    }

    public Double getValue_09() {
        return value_09;
    }

    public void setValue_09(Double value_09) {
        this.value_09 = value_09;
    }

    public Double getValue_10() {
        return value_10;
    }

    public void setValue_10(Double value_10) {
        this.value_10 = value_10;
    }

    public Double getValue_11() {
        return value_11;
    }

    public void setValue_11(Double value_11) {
        this.value_11 = value_11;
    }

    public Double getValue_12() {
        return value_12;
    }

    public void setValue_12(Double value_12) {
        this.value_12 = value_12;
    }

    public Double getValue_13() {
        return value_13;
    }

    public void setValue_13(Double value_13) {
        this.value_13 = value_13;
    }

    public Double getValue_14() {
        return value_14;
    }

    public void setValue_14(Double value_14) {
        this.value_14 = value_14;
    }

    public Double getValue_15() {
        return value_15;
    }

    public void setValue_15(Double value_15) {
        this.value_15 = value_15;
    }

    public Double getValue_16() {
        return value_16;
    }

    public void setValue_16(Double value_16) {
        this.value_16 = value_16;
    }

    public Double getValue_17() {
        return value_17;
    }

    public void setValue_17(Double value_17) {
        this.value_17 = value_17;
    }

    public Double getValue_18() {
        return value_18;
    }

    public void setValue_18(Double value_18) {
        this.value_18 = value_18;
    }

    public Double getValue_19() {
        return value_19;
    }

    public void setValue_19(Double value_19) {
        this.value_19 = value_19;
    }

    public Double getValue_20() {
        return value_20;
    }

    public void setValue_20(Double value_20) {
        this.value_20 = value_20;
    }

    public Double getValue_21() {
        return value_21;
    }

    public void setValue_21(Double value_21) {
        this.value_21 = value_21;
    }

    public Double getValue_22() {
        return value_22;
    }

    public void setValue_22(Double value_22) {
        this.value_22 = value_22;
    }

    public Double getValue_23() {
        return value_23;
    }

    public void setValue_23(Double value_23) {
        this.value_23 = value_23;
    }

    public Double getValue_24() {
        return value_24;
    }

    public void setValue_24(Double value_24) {
        this.value_24 = value_24;
    }

    public Double getValue_25() {
        return value_25;
    }

    public void setValue_25(Double value_25) {
        this.value_25 = value_25;
    }

    public Double getValue_26() {
        return value_26;
    }

    public void setValue_26(Double value_26) {
        this.value_26 = value_26;
    }

    public Double getValue_27() {
        return value_27;
    }

    public void setValue_27(Double value_27) {
        this.value_27 = value_27;
    }

    public Double getValue_28() {
        return value_28;
    }

    public void setValue_28(Double value_28) {
        this.value_28 = value_28;
    }

    public Double getValue_29() {
        return value_29;
    }

    public void setValue_29(Double value_29) {
        this.value_29 = value_29;
    }

    public Double getValue_30() {
        return value_30;
    }

    public void setValue_30(Double value_30) {
        this.value_30 = value_30;
    }

    public Double getValue_31() {
        return value_31;
    }

    public void setValue_31(Double value_31) {
        this.value_31 = value_31;
    }

    public Double getValue_32() {
        return value_32;
    }

    public void setValue_32(Double value_32) {
        this.value_32 = value_32;
    }

    public Double getValue_33() {
        return value_33;
    }

    public void setValue_33(Double value_33) {
        this.value_33 = value_33;
    }

    public Double getValue_34() {
        return value_34;
    }

    public void setValue_34(Double value_34) {
        this.value_34 = value_34;
    }

    public Double getValue_35() {
        return value_35;
    }

    public void setValue_35(Double value_35) {
        this.value_35 = value_35;
    }

    public Double getValue_36() {
        return value_36;
    }

    public void setValue_36(Double value_36) {
        this.value_36 = value_36;
    }

    public Double getValue_37() {
        return value_37;
    }

    public void setValue_37(Double value_37) {
        this.value_37 = value_37;
    }

    public Double getValue_38() {
        return value_38;
    }

    public void setValue_38(Double value_38) {
        this.value_38 = value_38;
    }

    public Double getValue_39() {
        return value_39;
    }

    public void setValue_39(Double value_39) {
        this.value_39 = value_39;
    }

    public Double getValue_40() {
        return value_40;
    }

    public void setValue_40(Double value_40) {
        this.value_40 = value_40;
    }

    public Double getValue_41() {
        return value_41;
    }

    public void setValue_41(Double value_41) {
        this.value_41 = value_41;
    }

    public Double getValue_42() {
        return value_42;
    }

    public void setValue_42(Double value_42) {
        this.value_42 = value_42;
    }

    public Double getValue_43() {
        return value_43;
    }

    public void setValue_43(Double value_43) {
        this.value_43 = value_43;
    }

    public Double getValue_44() {
        return value_44;
    }

    public void setValue_44(Double value_44) {
        this.value_44 = value_44;
    }

    public Double getValue_45() {
        return value_45;
    }

    public void setValue_45(Double value_45) {
        this.value_45 = value_45;
    }

    public Double getValue_46() {
        return value_46;
    }

    public void setValue_46(Double value_46) {
        this.value_46 = value_46;
    }

    public Double getValue_47() {
        return value_47;
    }

    public void setValue_47(Double value_47) {
        this.value_47 = value_47;
    }

    public Double getValue_48() {
        return value_48;
    }

    public void setValue_48(Double value_48) {
        this.value_48 = value_48;
    }

    public Double getValue_49() {
        return value_49;
    }

    public void setValue_49(Double value_49) {
        this.value_49 = value_49;
    }

    public Double getValue_50() {
        return value_50;
    }

    public void setValue_50(Double value_50) {
        this.value_50 = value_50;
    }

    public Double getValue_51() {
        return value_51;
    }

    public void setValue_51(Double value_51) {
        this.value_51 = value_51;
    }

    public Double getValue_52() {
        return value_52;
    }

    public void setValue_52(Double value_52) {
        this.value_52 = value_52;
    }

    public Double getValue_53() {
        return value_53;
    }

    public void setValue_53(Double value_53) {
        this.value_53 = value_53;
    }

    public Double getValue_54() {
        return value_54;
    }

    public void setValue_54(Double value_54) {
        this.value_54 = value_54;
    }

    public Double getValue_55() {
        return value_55;
    }

    public void setValue_55(Double value_55) {
        this.value_55 = value_55;
    }

    @Override
    public String toString() {
        return "SingleCityDataset{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", value_01=" + value_01 +
                ", value_02=" + value_02 +
                ", value_03=" + value_03 +
                ", value_04=" + value_04 +
                ", value_05=" + value_05 +
                ", value_06=" + value_06 +
                ", value_07=" + value_07 +
                ", value_08=" + value_08 +
                ", value_09=" + value_09 +
                ", value_10=" + value_10 +
                ", value_11=" + value_11 +
                ", value_12=" + value_12 +
                ", value_13=" + value_13 +
                ", value_14=" + value_14 +
                ", value_15=" + value_15 +
                ", value_16=" + value_16 +
                ", value_17=" + value_17 +
                ", value_18=" + value_18 +
                ", value_19=" + value_19 +
                ", value_20=" + value_20 +
                ", value_21=" + value_21 +
                ", value_22=" + value_22 +
                ", value_23=" + value_23 +
                ", value_24=" + value_24 +
                ", value_25=" + value_25 +
                ", value_26=" + value_26 +
                ", value_27=" + value_27 +
                ", value_28=" + value_28 +
                ", value_29=" + value_29 +
                ", value_30=" + value_30 +
                ", value_31=" + value_31 +
                ", value_32=" + value_32 +
                ", value_33=" + value_33 +
                ", value_34=" + value_34 +
                ", value_35=" + value_35 +
                ", value_36=" + value_36 +
                ", value_37=" + value_37 +
                ", value_38=" + value_38 +
                ", value_39=" + value_39 +
                ", value_40=" + value_40 +
                ", value_41=" + value_41 +
                ", value_42=" + value_42 +
                ", value_43=" + value_43 +
                ", value_44=" + value_44 +
                ", value_45=" + value_45 +
                ", value_46=" + value_46 +
                ", value_47=" + value_47 +
                ", value_48=" + value_48 +
                ", value_49=" + value_49 +
                ", value_50=" + value_50 +
                ", value_51=" + value_51 +
                ", value_52=" + value_52 +
                ", value_53=" + value_53 +
                ", value_54=" + value_54 +
                ", value_55=" + value_55 +
                '}';
    }
}
