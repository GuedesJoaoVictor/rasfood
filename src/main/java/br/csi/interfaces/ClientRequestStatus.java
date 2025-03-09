package br.csi.interfaces;


public interface ClientRequestStatus {
    String City = " and upper(a.city) = upper(:city)";
    String State = " and upper(a.state) = upper(:state)";
    String Street = " and upper(a.street) = upper(:street)";
}
