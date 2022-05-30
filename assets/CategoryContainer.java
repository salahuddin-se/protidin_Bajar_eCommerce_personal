/// 08/02/22
import 'dart:convert';
import 'dart:developer';

import 'package:customer_ui/all_screen/category_wise_separate.dart';
import 'package:customer_ui/all_screen/product_details.dart';
import 'package:customer_ui/components/size_config.dart';
import 'package:customer_ui/components/styles.dart';
import 'package:customer_ui/components/utils.dart';
import 'package:customer_ui/controller/cartItemsController.dart';
import 'package:customer_ui/dataModel/breat_biscuit.dart';
import 'package:customer_ui/dataModel/category_data_model.dart';
import 'package:customer_ui/dataModel/order_product_model.dart';
import 'package:customer_ui/dataModel/product_response.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:http/http.dart';

class CategoryContainer extends StatefulWidget {
  const CategoryContainer({
    Key? key,
    required this.categoryName,
    required this.nameNo,
    required this.large_Banner,
    required this.add_banner,
    required this.currentUserId,
    required this.currentEbStoreId,
    required this.catImage,
  }) : super(key: key);
  final String categoryName;
  final int nameNo;
  final String catImage;
  final String large_Banner;
  final String add_banner;
  final int currentUserId;
  final int currentEbStoreId;

  @override
  _CategoryContainerState createState() => _CategoryContainerState();
}

class _CategoryContainerState extends State<CategoryContainer> {
  var categoryData = [];

  var valueOne = "all";
  var categoryItemData = "Top Deals";
  var relatedProductsLink = "";
  //int lastPage = 1;
  late final int cat_id;
  var adt;
  List<Product> listOfProducts = [];

  //final PagingController<int, Product> _controller = PagingController(firstPageKey: 1);

  bool initPage = true;

  var controller = Get.put(CartItemsController());

  Future<void> getCategoryData({required String name}) async {
    log("widget name $name");

    String groceryURl = "http://test.protidin.com.bd:88/api/v2/sub-categories/$name";

    final response3 = await get(Uri.parse(groceryURl), headers: {"Accept": "application/json"});

    var groceryDataMap = jsonDecode(response3.body);

    if (groceryDataMap["success"] == true) {
      var categoryDataModel = CategoryDataModel.fromJson(groceryDataMap);

      categoryData = categoryDataModel.data;
      // categoryItemData = categoryDataModel.data[0].name;
      relatedProductsLink = categoryData[0].links.products;

      box.write(related_product_link, relatedProductsLink);
      setState(() {});
      // await fetchProducts(categoryDataModel.data[0].links.products);
    } else {}
  }

  // Future fetchProducts(link2) async {
  //   listOfProducts.clear();
  //   log("tap link $link2");
  //   log("new user id ${box.read(user_Id)}");
  //   log("new web store id ${box.read(webStoreId)}");
  //
  //   var response = await get(Uri.parse(link2));
  //   var productResponse = productMiniResponseFromJson(response.body.toString());
  //
  //   setState(() {
  //     listOfProducts = productResponse.products!
  //         .where(
  //           //(ele) => ele.user_id == widget.currentEbStoreId || ele.user_id == widget.currentEbStoreId,
  //           //(ele) => ele.user_id == box.read(userID) || ele.user_id == box.read(webStoreId),
  //           (ele) => ele.user_id == widget.currentEbStoreId || ele.user_id == widget.currentEbStoreId,
  //         )
  //         .toList();
  //   });
  //
  //   print("LIST_PRODUCT: ${listOfProducts.length}");
  // }

  Future fetchProducts(link2) async {
    listOfProducts.clear();
    log("tap link $link2");
    log("new user id ${box.read(user_Id)}");
    log("new web store id ${box.read(webStoreId)}");

    var response = await get(Uri.parse(link2));
    var productResponse = productMiniResponseFromJson(response.body.toString());

    for (var ele in productResponse.products!) {
      if (ele.user_id == box.read(user_Id) || ele.user_id == box.read(webStoreId)) {
        var lPProduct = ele.user_id == box.read(user_Id);
        box.write(physical_product, lPProduct);

        log(" name  ${ele.name}");
        listOfProducts.add(Product(
            name: ele.name,
            thumbnail_image: ele.thumbnail_image,
            base_discounted_price: ele.base_discounted_price,
            shop_name: ele.shop_name,
            base_price: ele.base_price,
            unit: ele.unit,
            id: ele.id,
            links: ele.links!,
            discount: ele.discount!,
            has_discount: ele.has_discount,
            user_id: ele.user_id));
        //log("product length ${listOfProducts.length}");
        setState(() {});
      }
    }

    print("LIST_PRODUCT: ${listOfProducts.length}");
    listOfProducts.sort((first, next) => next.discount!.compareTo(first.discount!));

    return listOfProducts;
  }

  var categoryProducts = [];
  List allCategoryProducts = [];

  var allRelatedProductsLink = "";
  Future _getProductsByCategory({required String cateId}) async {
    //categoryProducts.clear();

    final response12 =
        await get(Uri.parse("http://test.protidin.com.bd:88/api/v2/products/category/$cateId"), headers: {"Accept": "application/json"});

    var biscuitSweetsDataMap = jsonDecode(response12.body);

    if (biscuitSweetsDataMap["success"] == true) {
      //log("category data after tap $biscuitSweetsDataMap");

      var biscuitSweetsDataModel = BreadBiscuit.fromJson(biscuitSweetsDataMap);
      //var biscuitSweetsDataModel = BreadBiscuit.fromJson(biscuitSweetsDataMap);
      List<ProductsData> prodData = biscuitSweetsDataModel.data;

      allCategoryProducts =
          prodData.where((element) => element.userId == box.read(user_Id) || element.userId == box.read(webStoreId)).toList();

      //allRelatedProductsLink = allCategoryProducts[0].toString();
      setState(() {});
    } else {}
    // log("after decode $dataMap");
  }

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    log("name no ${widget.nameNo}");
    getCategoryData(name: widget.nameNo.toString());
    //_controller.addPageRequestListener((pageKey) {_fetchPage(pageKey);});
    _getProductsByCategory(cateId: widget.nameNo.toString());

    setState(() {
      fetchProducts(listOfProducts);
    });

    ///fetchProducts("link2");
  }

  @override
  Widget build(BuildContext context) {
    SizeConfig().init(context);
    var width = SizeConfig.screenWidth;
    var height = SizeConfig.screenHeight;
    var block = SizeConfig.block;
    return Column(
      children: [
        Container(
          width: MediaQuery.of(context).size.width / 1,
          decoration: BoxDecoration(
            borderRadius: BorderRadius.circular(15.0),
            color: Colors.white,
          ),
          child: Column(
            children: [
              SizedBox(
                height: 25,
              ),

              SizedBox(
                width: MediaQuery.of(context).size.width / 1.1,
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Text(
                      widget.categoryName,
                      style: TextStyle(color: Color(0xFF515151), fontSize: 25, fontWeight: FontWeight.w700, fontFamily: "CeraProBold"),
                    ),
                    InkWell(
                      onTap: () {
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                            builder: (context) => GroceryOfferPage(
                              ///categoryLink: categoryItemData,
                              receiveCategoryName: widget.categoryName,
                              receiveLargeBanner: widget.large_Banner,
                              categoryData: categoryData,
                            ),
                          ),
                        );
                      },
                      child: Container(
                        child: Text(
                          "VIEW ALL",
                          style: TextStyle(color: Color(0xFF515151), fontSize: 12, fontWeight: FontWeight.w400),
                        ),
                      ),
                    ),
                  ],
                ),
              ),

              SizedBox(
                height: 5,
              ),

              ///large_banner
              Container(
                height: 100,
                decoration: BoxDecoration(
                  //color: Colors.grey.withOpacity(0.2),
                  borderRadius: BorderRadius.circular(10.0),
                ),
                //width: double.infinity,
                width: MediaQuery.of(context).size.width / 1.1,
                child: Image.network(
                  imagePath + widget.large_Banner,
                  fit: BoxFit.cover,
                ),
              ),

              sized20,

              Container(
                //height: height * 0.22,
                height: height * 0.23,
                width: width,
                child: ListView.builder(
                  shrinkWrap: true,
                  scrollDirection: Axis.horizontal,
                  itemCount: categoryData.length + 1,
                  itemBuilder: (_, index) {
                    return GestureDetector(
                      onTap: () {
                        setState(
                          () {
                            categoryItemData = index == 0 ? 'Top Deals' : categoryData[index - 1].name;
                            initPage = index == 0;
                            if (index != 0) relatedProductsLink = categoryData[index - 1].links.products;
                            valueOne = index == 0 ? 'all' : (index - 1).toString();
                            log("related link $relatedProductsLink");
                            if (index != 0) fetchProducts(categoryData[index - 1].links.products);
                          },
                        );
                      },
                      child: Container(
                        height: height * 0.2,
                        width: width * 0.35,
                        margin: EdgeInsets.symmetric(vertical: 5, horizontal: 0),
                        decoration: BoxDecoration(
                          //color: Colors.grey.withOpacity(0.2),
                          borderRadius: BorderRadius.circular(10.0),
                        ),
                        child: Column(
                          children: [
                            Container(
                              height: height * 0.15,
                              //width: width*0.30,
                              width: width * 0.30,
                              decoration: BoxDecoration(
                                  color: valueOne.toString() == (index - 1).toString() || (valueOne.toString() == 'all' && index == 0)
                                      ? Colors.white
                                      : Color(0xFFF0E6F2),
                                  borderRadius: BorderRadius.circular(15.0)),
                              child: Center(
                                child: Padding(
                                  padding: const EdgeInsets.all(0),
                                  child: index == 0
                                      ? Image.network(
                                          imagePath + widget.catImage,
                                        )
                                      : categoryData[index - 1].mobileBanner.isEmpty
                                          ? Image.asset("assets/app_logo.png")
                                          : Image.network(
                                              imagePath + categoryData[index - 1].mobileBanner,
                                            ),
                                ),
                              ),
                            ),
                            sized5,
                            Text(
                              index == 0 ? 'Top Deals' : categoryData[index - 1].name,
                              style: TextStyle(
                                  color: Color(0xFF515151), fontSize: 16, fontWeight: FontWeight.w700, fontFamily: "ceraProMedium"),
                              textAlign: TextAlign.center,
                            ),
                          ],
                        ),
                      ),
                    );
                  },
                ),
              ),

              SizedBox(
                height: 5,
              ),
              //sized20,

              Padding(
                padding: const EdgeInsets.only(left: 15.0),
                child: Align(
                  alignment: Alignment.centerLeft,
                  child: Text(
                    categoryItemData,
                    style: TextStyle(color: Color(0xFF515151), fontSize: 20, fontWeight: FontWeight.w700, fontFamily: "CeraProBold"),
                  ),
                ),
              ),
              sized20,
              Padding(
                padding: const EdgeInsets.fromLTRB(15, 0, 8, 0),
                child: Container(
                    height: height * 0.35,
                    width: width,
                    child: initPage
                        ? ListView.builder(
                            shrinkWrap: true,
                            scrollDirection: Axis.horizontal,
                            itemCount: allCategoryProducts.length,
                            itemBuilder: (_, index) {
                              return Padding(
                                padding: const EdgeInsets.only(right: 8.0),
                                child: Container(
                                  width: MediaQuery.of(context).size.width / 2.1,
                                  decoration: BoxDecoration(color: Color(0xFFF4F1F5), borderRadius: BorderRadius.circular(15.0)),
                                  //height: MediaQuery.of(context).size.height/3.2,width: MediaQuery.of(context).size.width / 2.34,
                                  child: Column(
                                    mainAxisAlignment: MainAxisAlignment.center,
                                    crossAxisAlignment: CrossAxisAlignment.start,
                                    children: [
                                      Align(
                                        alignment: Alignment.centerLeft,
                                        child: Container(
                                          width: MediaQuery.of(context).size.width / 5,

                                          //height: MediaQuery.of(context).size.height / 41,
                                          height: MediaQuery.of(context).size.height / 45,
                                          margin: EdgeInsets.only(top: 10),
                                          decoration: BoxDecoration(
                                            color: allCategoryProducts[index].hasDiscount == true ? Color(0xFF10AA2A) : Color(0xFFF4F1F5),
                                            borderRadius:
                                                BorderRadius.only(topRight: Radius.circular(4.0), bottomRight: Radius.circular(4.0)),
                                          ),
                                          //

                                          child: Align(
                                            alignment: Alignment.centerLeft,
                                            child: allCategoryProducts[index].hasDiscount == true
                                                ? Padding(
                                                    padding: const EdgeInsets.only(left: 3.0),
                                                    child: Text(
                                                      //"15% OFF",
                                                      "${allCategoryProducts[index].discount.toString()}TK OFF",
                                                      style: TextStyle(color: Colors.white, fontSize: 13, fontWeight: FontWeight.w700),
                                                    ),
                                                  )
                                                : Text(
                                                    //"15% OFF",
                                                    "",
                                                    style: TextStyle(color: Colors.white, fontSize: 12, fontWeight: FontWeight.w500),
                                                  ),
                                          ),
                                        ),
                                      ),

                                      ///
                                      GestureDetector(
                                        onTap: () {
                                          log("details ${allCategoryProducts[index].links!.details}");
                                          Navigator.push(
                                            context,
                                            MaterialPageRoute(
                                              builder: (context) => ProductDetails(
                                                detailsLink: allCategoryProducts[index].links!.details!,
                                                relatedProductLink: relatedProductsLink,
                                              ),

                                              ///relatedProductLink: relatedProductsLink
                                            ),
                                          );
                                        },
                                        child: Container(
                                          child: Image.network(imagePath + allCategoryProducts[index].thumbnailImage.toString()),
                                          //height: MediaQuery.of(context).size.height / 7.9,
                                          height: MediaQuery.of(context).size.height / 7.75,
                                          //width: MediaQuery.of(context).size.width / 2,
                                          width: MediaQuery.of(context).size.width / 1.7,
                                        ),
                                      ),

                                      ///

                                      InkWell(
                                        onTap: () {
                                          log("details ${allCategoryProducts[index].links!.details}");
                                          Navigator.push(
                                            context,
                                            MaterialPageRoute(
                                              builder: (context) => ProductDetails(
                                                  detailsLink: allCategoryProducts[index].links!.details!,
                                                  relatedProductLink: relatedProductsLink),
                                            ),
                                          );
                                        },
                                        child: FittedBox(
                                          child: Container(
                                            ///height: height! * 0.08,
                                            width: MediaQuery.of(context).size.width / 2,
                                            height: MediaQuery.of(context).size.height / 14.2,
                                            child: Padding(
                                              padding: const EdgeInsets.fromLTRB(5, 5, 5, 0),
                                              child: Center(
                                                child: Text(
                                                  allCategoryProducts[index].name.toString(),
                                                  style: TextStyle(
                                                    color: Color(0xFF515151),
                                                    fontSize: 15.3111,
                                                    //fontWeight: FontWeight.w300,
                                                    fontFamily: "CeraProMedium",
                                                    fontWeight: FontWeight.w500,
                                                    fontStyle: FontStyle.normal,
                                                  ),
                                                  maxLines: 2,
                                                  textAlign: TextAlign.center,
                                                ),
                                              ),
                                            ),
                                          ),
                                        ),
                                      ),

                                      Align(
                                        alignment: Alignment.center,
                                        child: Container(
                                          height: MediaQuery.of(context).size.height / 36,
                                          width: MediaQuery.of(context).size.width / 2,
                                          child: Center(
                                            child: Text(
                                              allCategoryProducts[index].unit.toString(),
                                              style: TextStyle(
                                                color: Colors.grey.withOpacity(0.9),
                                                fontSize: 15,
                                                fontWeight: FontWeight.w400,
                                                fontFamily: 'CeraProMedium',
                                              ),
                                            ),
                                          ),
                                        ),
                                      ),

                                      Padding(
                                        padding: const EdgeInsets.fromLTRB(0, 0, 0, 0),
                                        child: Center(
                                          child: Container(
                                            height: MediaQuery.of(context).size.height / 22,

                                            ///width: MediaQuery.of(context).size.width / 2.34,
                                            width: MediaQuery.of(context).size.width / 2.0,
                                            child: Row(
                                              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                                              children: [
                                                Container(
                                                  child: Image.asset(
                                                    "assets/p.png",
                                                    height: 22,
                                                    width: 22,
                                                  ),
                                                  height: 22,
                                                  width: 22,
                                                ),
                                                Text(
                                                  allCategoryProducts[index].baseDiscountedPrice.toString(),
                                                  style: TextStyle(
                                                    color: Color(0xFF515151),
                                                    fontSize: 19,
                                                    fontFamily: 'CeraProMedium',
                                                    fontWeight: FontWeight.w700,
                                                  ),
                                                ),
                                                allCategoryProducts[index].baseDiscountedPrice == allCategoryProducts[index].basePrice
                                                    ? Container(width: 20, child: Text(""))
                                                    : Text(
                                                        allCategoryProducts[index].basePrice.toString(),
                                                        style: TextStyle(
                                                            color: Color(0xFFA299A8),
                                                            fontSize: 13,
                                                            fontWeight: FontWeight.w400,
                                                            fontFamily: 'CeraProMedium',
                                                            decoration: TextDecoration.lineThrough),
                                                      ),
                                                Padding(
                                                  padding: const EdgeInsets.only(left: 11.4),
                                                ),
                                                InkWell(
                                                  onTap: () {
                                                    controller.addToCart(
                                                        OrderItemModel(
                                                          productId: allCategoryProducts[index].id,
                                                          price: int.tryParse(
                                                              allCategoryProducts[index].basePrice!.toString().replaceAll('৳', '')),
                                                          productThumbnailImage: allCategoryProducts[index].thumbnailImage,
                                                          productName: allCategoryProducts[index].name,
                                                          quantity: 1,
                                                          userId: box.read(userID),
                                                          variant: '',
                                                          discount: allCategoryProducts[index].discount,
                                                          unit: int.tryParse(allCategoryProducts[index].unit!.toString()),
                                                          discountType: allCategoryProducts[index].discountType,
                                                        ),
                                                        context);
                                                  },
                                                  child: Container(
                                                    height: 40,
                                                    width: 40,
                                                    //decoration: BoxDecoration(color: kPrimaryColor, shape: BoxShape.circle),
                                                    child: Center(
                                                      child: Image.asset(
                                                        "assets/img_193.png",
                                                        height: 40,
                                                        width: 40,
                                                      ),
                                                    ),
                                                  ),
                                                ),
                                              ],
                                            ),
                                          ),
                                        ),
                                      ),

                                      SizedBox(
                                        height: 4.2,
                                      ),
                                      Container(
                                        //height: height! * 0.03,
                                        height: MediaQuery.of(context).size.height / 28,
                                        //width: MediaQuery.of(context).size.width / 2.34,
                                        width: MediaQuery.of(context).size.width / 2.0,
                                        decoration: BoxDecoration(
                                            color: Color(0xFFDDEAE1),
                                            borderRadius:
                                                BorderRadius.only(bottomLeft: Radius.circular(10.0), bottomRight: Radius.circular(10.0))),
                                        child: Padding(
                                          padding: const EdgeInsets.fromLTRB(1, 0, 1, 5),
                                          child: Row(
                                            mainAxisAlignment: MainAxisAlignment.center,
                                            crossAxisAlignment: CrossAxisAlignment.center,
                                            children: [
                                              Container(
                                                child: Image.asset("assets/img_42.png"),
                                                height: 17,
                                                width: 15,
                                              ),
                                              Padding(
                                                padding: const EdgeInsets.fromLTRB(5, 3, 0, 0),
                                                child: Text(
                                                  "  Earning  +৳0",
                                                  style: TextStyle(
                                                    fontSize: 13,
                                                    color: Colors.green,
                                                    fontWeight: FontWeight.w400,
                                                    fontFamily: 'CeraProMedium',
                                                  ),
                                                ),
                                              ),
                                            ],
                                          ),
                                        ),
                                      ),
                                    ],
                                  ),
                                ),
                              );
                            })
                        : ListView.builder(
                            shrinkWrap: true,
                            scrollDirection: Axis.horizontal,
                            itemCount: listOfProducts.length,
                            itemBuilder: (_, index) {
                              return Padding(
                                padding: const EdgeInsets.only(right: 8.0),
                                child: Container(
                                  width: MediaQuery.of(context).size.width / 2.1,
                                  decoration: BoxDecoration(color: Color(0xFFF4F1F5), borderRadius: BorderRadius.circular(15.0)),
                                  //height: MediaQuery.of(context).size.height/3.2,width: MediaQuery.of(context).size.width / 2.34,
                                  child: Column(
                                    mainAxisAlignment: MainAxisAlignment.center,
                                    crossAxisAlignment: CrossAxisAlignment.start,
                                    children: [
                                      Align(
                                        alignment: Alignment.centerLeft,
                                        child: Container(
                                          width: MediaQuery.of(context).size.width / 5,

                                          height: MediaQuery.of(context).size.height / 45,
                                          margin: EdgeInsets.only(top: 10),
                                          decoration: BoxDecoration(
                                            color: listOfProducts[index].has_discount == true ? Color(0xFF10AA2A) : Color(0xFFF4F1F5),
                                            borderRadius:
                                                BorderRadius.only(topRight: Radius.circular(4.0), bottomRight: Radius.circular(4.0)),
                                          ),
                                          //

                                          child: Align(
                                            alignment: Alignment.centerLeft,
                                            child: listOfProducts[index].has_discount == true
                                                ? Padding(
                                                    padding: const EdgeInsets.only(left: 3.0),
                                                    child: Text(
                                                      //"15% OFF",
                                                      "${listOfProducts[index].discount.toString()}TK OFF",
                                                      style: TextStyle(color: Colors.white, fontSize: 13, fontWeight: FontWeight.w700),
                                                    ),
                                                  )
                                                : Text(
                                                    //"15% OFF",
                                                    "",
                                                    style: TextStyle(color: Colors.white, fontSize: 12, fontWeight: FontWeight.w500),
                                                  ),
                                          ),
                                        ),
                                      ),

                                      ///
                                      GestureDetector(
                                        onTap: () {
                                          log("details ${listOfProducts[index].links!.details}");
                                          Navigator.push(
                                            context,
                                            MaterialPageRoute(
                                              builder: (context) => ProductDetails(
                                                  detailsLink: listOfProducts[index].links!.details!,
                                                  relatedProductLink: relatedProductsLink),
                                            ),
                                          );
                                        },
                                        child: Container(
                                          child: Image.network(imagePath + listOfProducts[index].thumbnail_image.toString()),
                                          height: MediaQuery.of(context).size.height / 7.75,
                                          width: MediaQuery.of(context).size.width / 1.7,
                                        ),
                                      ),

                                      ///

                                      InkWell(
                                        onTap: () {
                                          log("details ${listOfProducts[index].links!.details}");
                                          Navigator.push(
                                            context,
                                            MaterialPageRoute(
                                              builder: (context) => ProductDetails(
                                                  detailsLink: listOfProducts[index].links!.details!,
                                                  relatedProductLink: relatedProductsLink),
                                            ),
                                          );
                                        },
                                        child: FittedBox(
                                          child: Container(
                                            ///height: height! * 0.08,
                                            width: MediaQuery.of(context).size.width / 2,
                                            height: MediaQuery.of(context).size.height / 14.5,
                                            child: Padding(
                                              padding: const EdgeInsets.fromLTRB(5, 5, 5, 0),
                                              child: Center(
                                                child: Text(
                                                  listOfProducts[index].name.toString(),
                                                  style: TextStyle(
                                                    color: Color(0xFF515151),
                                                    fontSize: 15.3111,
                                                    //fontWeight: FontWeight.w300,
                                                    fontFamily: "CeraProMedium",
                                                    fontWeight: FontWeight.w500,
                                                    fontStyle: FontStyle.normal,
                                                  ),
                                                  maxLines: 2,
                                                  textAlign: TextAlign.center,
                                                ),
                                              ),
                                            ),
                                          ),
                                        ),
                                      ),

                                      Align(
                                        alignment: Alignment.center,
                                        child: Container(
                                          height: MediaQuery.of(context).size.height / 36,
                                          width: MediaQuery.of(context).size.width / 2,
                                          child: Center(
                                            child: Text(
                                              listOfProducts[index].unit.toString(),
                                              style: TextStyle(
                                                color: Colors.grey.withOpacity(0.9),
                                                fontSize: 15,
                                                fontWeight: FontWeight.w400,
                                                fontFamily: 'CeraProMedium',
                                              ),
                                            ),
                                          ),
                                        ),
                                      ),

                                      Padding(
                                        padding: const EdgeInsets.fromLTRB(0, 0, 0, 0),
                                        child: Center(
                                          child: Container(
                                            height: MediaQuery.of(context).size.height / 22,

                                            ///width: MediaQuery.of(context).size.width / 2.34,
                                            width: MediaQuery.of(context).size.width / 2.0,
                                            child: Row(
                                              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                                              children: [
                                                Container(
                                                  child: Image.asset(
                                                    "assets/p.png",
                                                    height: 22,
                                                    width: 22,
                                                  ),
                                                  height: 22,
                                                  width: 22,
                                                ),
                                                Text(listOfProducts[index].base_discounted_price.toString(),
                                                    style: TextStyle(
                                                      color: Color(0xFF515151),
                                                      fontSize: 19,
                                                      fontFamily: 'CeraProMedium',
                                                      fontWeight: FontWeight.w700,
                                                    )),
                                                listOfProducts[index].base_discounted_price == listOfProducts[index].base_price
                                                    ? Container(width: 20, child: Text(""))
                                                    : Text(
                                                        listOfProducts[index].base_price.toString(),
                                                        style: TextStyle(
                                                            color: Color(0xFFA299A8),
                                                            fontSize: 13,
                                                            fontWeight: FontWeight.w400,
                                                            fontFamily: 'CeraProMedium',
                                                            decoration: TextDecoration.lineThrough),
                                                      ),
                                                Padding(
                                                  padding: const EdgeInsets.only(left: 10.9),
                                                ),
                                                InkWell(
                                                  onTap: () {
                                                    controller.addToCart(
                                                        OrderItemModel(
                                                            productId: listOfProducts[index].id,
                                                            price: int.tryParse(
                                                                listOfProducts[index].base_price!.toString().replaceAll('৳', '')),
                                                            productThumbnailImage: listOfProducts[index].thumbnail_image,
                                                            productName: listOfProducts[index].name,
                                                            quantity: 1,
                                                            userId: box.read(userID),
                                                            variant: '',
                                                            discount: listOfProducts[index].discount,
                                                            unit: int.tryParse(listOfProducts[index].unit!.toString()),
                                                            discountType: ''),
                                                        context);
                                                  },
                                                  child: Container(
                                                    height: 40,
                                                    width: 40,
                                                    //decoration: BoxDecoration(color: kPrimaryColor, shape: BoxShape.circle),
                                                    child: Center(
                                                      child: Image.asset(
                                                        "assets/img_193.png",
                                                        height: 40,
                                                        width: 40,
                                                      ),
                                                    ),
                                                  ),
                                                ),
                                              ],
                                            ),
                                          ),
                                        ),
                                      ),

                                      SizedBox(
                                        height: 4.2,
                                      ),
                                      Container(
                                        //height: height! * 0.03,
                                        height: MediaQuery.of(context).size.height / 28,
                                        //width: MediaQuery.of(context).size.width / 2.34,
                                        width: MediaQuery.of(context).size.width / 2.0,
                                        decoration: BoxDecoration(
                                            color: Color(0xFFDDEAE1),
                                            borderRadius:
                                                BorderRadius.only(bottomLeft: Radius.circular(10.0), bottomRight: Radius.circular(10.0))),
                                        child: Padding(
                                          padding: const EdgeInsets.fromLTRB(1, 0, 1, 5),
                                          child: Row(
                                            mainAxisAlignment: MainAxisAlignment.center,
                                            crossAxisAlignment: CrossAxisAlignment.center,
                                            children: [
                                              Container(
                                                child: Image.asset("assets/img_42.png"),
                                                height: 17,
                                                width: 15,
                                              ),
                                              Padding(
                                                padding: const EdgeInsets.fromLTRB(5, 3, 0, 0),
                                                child: Text(
                                                  "  Earning  +৳0",
                                                  style: TextStyle(
                                                    fontSize: 13,
                                                    color: Colors.green,
                                                    fontWeight: FontWeight.w400,
                                                    fontFamily: 'CeraProMedium',
                                                  ),
                                                ),
                                              ),
                                            ],
                                          ),
                                        ),
                                      ),
                                    ],
                                  ),
                                ),
                              );
                            })),
              ),

              SizedBox(
                height: 35,
              ),
            ],
          ),
        ),
        SizedBox(
          height: 32,
        ),
        Container(
          height: 200,
          decoration: BoxDecoration(
            //color: Colors.grey.withOpacity(0.2),
            borderRadius: BorderRadius.circular(10.0),
          ),
          //width: double.infinity,
          width: MediaQuery.of(context).size.width / 1.1,
          child: Image.network(
            imagePath + widget.add_banner,
            fit: BoxFit.cover,
          ),
        ),
      ],
    );
  }
}


