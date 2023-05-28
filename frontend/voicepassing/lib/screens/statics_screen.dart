import 'package:flutter/material.dart';
import 'package:voicepassing/screens/main_screen.dart';
import 'package:voicepassing/widgets/head_bar.dart';
import 'package:voicepassing/widgets/nav_bar.dart';
import 'package:voicepassing/widgets/statistics_widget/chart_box.dart';
import 'package:voicepassing/widgets/statistics_widget/keyword_box.dart';

class StaticsScreen extends StatefulWidget {
  const StaticsScreen({super.key});

  @override
  State<StaticsScreen> createState() => _ResultScreenState();
}

class _ResultScreenState extends State<StaticsScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: HeadBar(
        navPage: const MainScreen(),
        title: const Text('통계 및 사례'),
        appBar: AppBar(),
      ),
      body: Builder(
        builder: (BuildContext context) {
          return SingleChildScrollView(
            padding: const EdgeInsets.fromLTRB(24, 0, 24, 24),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                const SizedBox(
                  height: 20,
                ),
                ChartBox(),
                const KeywordBox(),
              ],
            ),
          );
        },
      ),
      bottomNavigationBar: const Navbar(selectedIndex: 2),
    );
  }
}
