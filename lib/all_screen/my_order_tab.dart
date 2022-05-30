import 'package:customer_ui/all_screen/ongoing_orders.dart';
import 'package:customer_ui/components/size_config.dart';
import 'package:customer_ui/components/styles.dart';
import 'package:flutter/material.dart';

import 'history_order.dart';
import 'order_canceled.dart';
import 'partial_order.dart';

class MyOrderTabBar extends StatefulWidget {
  const MyOrderTabBar({Key? key}) : super(key: key);

  @override
  _MyOrderTabBarState createState() => _MyOrderTabBarState();
}

class _MyOrderTabBarState extends State<MyOrderTabBar> with SingleTickerProviderStateMixin {
  late TabController controller;

  @override
  void initState() {
    // TODO: implement initState
    controller = TabController(length: 4, vsync: this);
  }

  @override
  void dispose() {
    // TODO: implement dispose
    controller.dispose();
  }

  @override
  Widget build(BuildContext context) {
    SizeConfig().init(context);
    var width = SizeConfig.screenWidth;
    var height = SizeConfig.screenHeight;
    var block = SizeConfig.block;
    return Scaffold(
      appBar: AppBar(
        elevation: 0.0,
        backgroundColor: kWhiteColor,
        centerTitle: true,
        title: Text(
          "My Order",
          style: TextStyle(color: kBlackColor, fontSize: block * 4),
        ),
        iconTheme: IconThemeData(color: kBlackColor),
      ),
      body: Container(
        height: height,
        width: width,
        child: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 10.0, vertical: 10.0),
          child: Column(
            children: [
              Container(
                decoration: BoxDecoration(
                  border: Border.all(
                    width: 0.150,
                    color: Colors.grey,
                  ),
                  color: Colors.white,
                  //borderRadius: BorderRadius.circular(10),
                  boxShadow: [
                    BoxShadow(
                        // color: Colors.grey.withOpacity(0.1),
                        // spreadRadius: 5, //spread radius
                        // blurRadius: 5, // blur radius
                        // offset: Offset(0, 2),

                        color: Colors.grey,
                        //blurRadius: 15.0,
                        offset: Offset(0.0, 0.75)),
                  ],
                ),
                height: 52,
                child: TabBar(
                  isScrollable: true,
                  indicatorColor: kPrimaryColor,
                  controller: controller,
                  tabs: const [
                    Padding(
                      padding: EdgeInsets.only(right: 25.0),
                      child: Text(
                        "Ongoing",
                        style: TextStyle(color: kBlackColor),
                      ),
                    ),
                    Padding(
                      padding: EdgeInsets.only(left: 25.0),
                      child: Text("Complete", style: TextStyle(color: kBlackColor)),
                    ),
                    Padding(
                      padding: EdgeInsets.only(left: 25.0),
                      child: Text("Partial", style: TextStyle(color: kBlackColor)),
                    ),
                    Padding(
                      padding: EdgeInsets.only(left: 25.0),
                      child: Text("Canceled", style: TextStyle(color: kBlackColor)),
                    ),
                  ],
                ),
              ),
              SizedBox(
                height: 10,
              ),
              Expanded(
                child: TabBarView(
                  controller: controller,
                  children: const [
                    OngoingOrder(),
                    HistoryOrder(),
                    PartialOrder(),
                    OrderCanceled(),
                  ],
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

/*
import 'package:customer_ui/all_screen/ongoing_orders.dart';
import 'package:customer_ui/components/size_config.dart';
import 'package:customer_ui/components/styles.dart';
import 'package:flutter/material.dart';

import 'history_order.dart';
import 'order_canceled.dart';

class MyOrderTabBar extends StatefulWidget {
  const MyOrderTabBar({Key? key}) : super(key: key);

  @override
  _MyOrderTabBarState createState() => _MyOrderTabBarState();
}

class _MyOrderTabBarState extends State<MyOrderTabBar> with SingleTickerProviderStateMixin {
  late TabController controller;

  @override
  void initState() {
    // TODO: implement initState
    controller = TabController(length: 3, vsync: this);
  }

  @override
  void dispose() {
    // TODO: implement dispose
    controller.dispose();
  }

  @override
  Widget build(BuildContext context) {
    SizeConfig().init(context);
    var width = SizeConfig.screenWidth;
    var height = SizeConfig.screenHeight;
    var block = SizeConfig.block;
    return Scaffold(
      appBar: AppBar(
        elevation: 0.0,
        backgroundColor: kWhiteColor,
        centerTitle: true,
        title: Text(
          "My Order",
          style: TextStyle(color: kBlackColor, fontSize: block * 4),
        ),
        iconTheme: IconThemeData(color: kBlackColor),
      ),
      body: SizedBox(
        height: height,
        width: width,
        child: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 10.0, vertical: 10.0),
          child: Column(
            children: [
              SizedBox(
                height: 50,
                child: TabBar(
                  isScrollable: true,
                  indicatorColor: kPrimaryColor,
                  controller: controller,
                  tabs: const [
                    Padding(
                      padding: EdgeInsets.only(right: 25.0),
                      child: Text(
                        "Ongoing",
                        style: TextStyle(color: kBlackColor),
                      ),
                    ),
                    Padding(
                      padding: EdgeInsets.only(left: 25.0),
                      child: Text("History", style: TextStyle(color: kBlackColor)),
                    ),
                    Padding(
                      padding: EdgeInsets.only(left: 25.0),
                      child: Text("Canceled", style: TextStyle(color: kBlackColor)),
                    ),
                  ],
                ),
              ),
              SizedBox(
                height: 10,
              ),
              Expanded(
                child: TabBarView(
                  controller: controller,
                  children: const [
                    OngoingOrder(),
                    HistoryOrder(),
                    OrderCanceled(),
                  ],
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

*/
