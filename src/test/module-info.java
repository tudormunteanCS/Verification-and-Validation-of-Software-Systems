module pizzashop.test {

    requires pizzashop; // needed to access main code
    requires org.junit.jupiter.api;
    requires org.junit.jupiter.params;
    requires org.junit.platform.commons;

    opens pizzashop.service to org.junit.platform.commons;
}
